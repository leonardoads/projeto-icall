/*
 * UPEK TouchChip driver for libfprint
 * Copyright (C) 2007 Jan-Michael Brummer <buzz2@gmx.de>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

#define FP_COMPONENT 		"upektc"

#include <errno.h>
#include <string.h>

#include <glib.h>
#include <libusb.h>

#include <fp_internal.h>

#define SENSOR_FULL_IMAGE	59904
#define WAIT_COUNT			5

typedef char sint8;
typedef unsigned char uint8;
typedef int sint32;
typedef unsigned int uint32;

/** scan command */
static const unsigned char anScanCommand[ 0x40 ] = {
	0x0e, 0x00, 0x03, 0xa8, 0x00, 0xb6, 0xbb, 0xbb,
	0xb8, 0xb7, 0xb8, 0xb5, 0xb8, 0xb9, 0xb8, 0xb9,
	0xbb, 0xbb, 0xbe, 0xbb, 0x4e, 0x16, 0xf4, 0x77,
	0xa8, 0x07, 0x32, 0x00, 0x6a, 0x16, 0xf4, 0x77,
	0x78, 0x24, 0x61, 0x00, 0xc8, 0x00, 0xec, 0x00,
	0x01, 0x00, 0x00, 0x00, 0x3c, 0xf3, 0x2f, 0x01,
	0x05, 0x90, 0xf6, 0x77, 0x84, 0xf5, 0x2f, 0x01,
	0x05, 0x90, 0xf6, 0x00, 0xc8, 0x00, 0xec, 0x00
};

/** init command */
static const unsigned char anInitCommand[ 0x40 ] = {
	0x03, 0x00, 0x00, 0x00, 0x02, 0xfb, 0x0f, 0x00,
	0xc4, 0xf9, 0x2f, 0x01, 0x6d, 0x4f, 0x01, 0x10,
	0x44, 0xf9, 0x2f, 0x01, 0x40, 0x00, 0x00, 0x00,
	0xe8, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
};

/**
 * \brief Common interaktion routine for the sensor device
 * \param dev fingerprint image device pointer
 * \param pnRawString raw data string
 * \param nLen length we want to read, if 0 do not read at all
 * \param pnBuffer buffer pointer we want to store the read buffer
 * \return error code
 */
static sint32 askScanner( struct fp_img_dev *dev, const unsigned char *pnRawString, sint32 nLen, sint8 *pnBuffer ) {
    sint8 anBuf[ 65535 ];
	sint32 nRet;
	int transferred;
	struct libusb_bulk_transfer msg1 = {
		.endpoint = 3,
		.data = pnRawString,
		.length = 0x40,
	};
	struct libusb_bulk_transfer msg2 = {
		.endpoint = 0x82,
		.data = anBuf,
		.length = nLen,
	};

	nRet = libusb_bulk_transfer(dev->udev, &msg1, &transferred, 1003);
	if (transferred != 0x40) {
		return -1;
	}

	if ( !nLen ) {
		return 0;
	}

	nRet = libusb_bulk_transfer(dev->udev, &msg2, &transferred, 1003);
	if ( ( transferred == nLen ) && ( pnBuffer != NULL ) ) {
		memcpy( pnBuffer, anBuf, nLen );
		return transferred;
	}

	return nRet;
}

/**
 * \brief Quick test if finger is on sensor
 * \param pnImage image pointer
 * \return 1 on yes, 0 on no
 */
static sint32 ValidScan( sint8 *pnImage ) {
	sint32 nIndex, nSum;

	nSum = 0;

	for ( nIndex = 0; nIndex < SENSOR_FULL_IMAGE; nIndex++ ) {
		if ( ( uint8 ) pnImage[ nIndex ] < 160 ) {
			nSum++;
		}
	}

	return nSum < 500 ? 0 : 1;
}

/**
 * \brief Setup Sensor device
 * \param dev fingerprint image device pointer
 * \return error code
 */
static sint32 SetupSensor( struct fp_img_dev *dev ) {
	libusb_claim_interface(dev->udev, 0);

	/* setup sensor */
	if ( askScanner( dev, "\x03\x00\x00\x00\x02\xfe\x00\x01\xc0\xbd\xf0\xff\xff\xff\xff\xff\x00\xf0\xfd\x7f\x00\x60\xfd\x7f\x14\x00\x00\x00\x01\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x10\x00\x00\x00\xcc\xf8\x2f\x01\x09\x48\xe7\x77\xf0\xfa\x2f\x01\x09\x48\xe7\x77\xe0\x3a\xe6\x77", 0x00, NULL ) < 0 ) {
		return -1;
	}

	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf7\x00\x00\xc8\x01\x00\x00\x40\x00\x00\x00\x01\x00\x00\x00\x58\xf9\x2f\x01\xe9\x4f\x01\x10\xd8\xf8\x2f\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x03\x00\x00\x00\x02\xfe\x00\x01\xc0\xbd\xf0\xff\xff\xff\xff\xff\x00\xf0\xfd\x7f", 0x40, NULL ) < 0 ) {
		return -2;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\xf7\xcd\x00\x2c\xf9\x2f\x01\x6d\x4f\x01\x10\xac\xf8\x2f\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x02\xfe\x16\x10\x03\xee\x00\x37\x01\x09\x02\x0e\x03\x18\x03\x1a\x03\x20\x10\x2f\x11\x3f\x12\x44\x01\x01\x07\x08\x0c\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -3;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf8\x00\x00\x02\xfe\x16\x10\x03\xee\x00\x37\x01\x09\x02\x0e\x03\x18\x03\x1a\x03\x20\x10\x2f\x11\x3f\x12\x44\x01\x01\x07\x08\x0c\x00\x6c\x6c\x00\xf9\x2f\x01\x97\x40\x01\x10\x03\x00\x00\x00\x00\x00\x00\x00\xfa\x45\x03\x10\x02\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -4;
	};

	if ( askScanner( dev, "\x8b\x00\x00\x00\x3a\x50\xf9\x2f\x01\x18\x00\x00\x00\xff\xff\xff\xff\x00\x00\x00\x00\x88\xf9\x2f\x01\x91\x99\x00\x10\xf8\x00\x00\x00\xbe\x99\x00\x10\xa0\xa6\x04\x10\x01\x9b\x00\x10\x18\x00\x00\x00\xff\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\xf8\x00\x00", 0x40, NULL ) < 0 ) {
		return -5;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x3a\x00\x01\x02\x04\x05\x06\x07\x08\x09\x0a\x0b\x0c\x0d\x0e\x0f\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x1a\x1b\x1c\x1d\x1f\x20\x21\x22\x23\x24\x27\x28\x29\x2a\x2b\x2c\x2d\x2e\x2f\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x3a\x3d\x3f\xff\x00", 0x40, NULL ) < 0 ) {
		return -6;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x3a\x00\x01\x02\x04\x05\x06\x07\x08\x09\x0a\x0b\x0c\x0d\x0e\x0f\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x1a\x1b\x1c\x1d\x1f\x20\x21\x22\x23\x24\x27\x28\x29\x2a\x2b\x2c\x2d\x2e\x2f\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x3a\x3d\x3f\xff\x00", 0x40, NULL ) < 0 ) {
		return -7;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\x0d\xff\x36\xdc\xf8\x2f\x01\xf1\x9d\x00\x10\xfc\xf8\x2f\x01\x9d\xf8\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x9e\xbf\x85\x85\x02\x05\x26\x25\x4d\x13\x10\x00\x00\x00\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -8;
	};
	if ( askScanner( dev, "\x03\x00\x00\x00\x0c\x37\x6a\x3d\x73\x3d\x71\x0e\x01\x0e\x81\x3d\x51\xf8\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x9e\xbf\x85\x85\x02\x05\x26\x25\x4d\x13\x10\x00\x00\x00\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\xf0\xf8\x2f\x01", 0x00, NULL ) < 0 ) {
		return -9;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x3a\x00\x01\x02\x04\x05\x06\x07\x08\x09\x0a\x0b\x0c\x0d\x0e\x0f\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x1a\x1b\x1c\x1d\x1f\x20\x21\x22\x23\x24\x27\x28\x29\x2a\x2b\x2c\x2d\x2e\x2f\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x3a\x3d\x3f\xff\x00", 0x40, NULL ) < 0 ) {
		return -10;
	};

	if ( askScanner( dev, "\x8b\x00\x01\x7c\x0a\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x7f\x14\xf5\x2f\x01\xa0\x20\x14\x00\x40\xf8\x2f\x01\x05\x90\xf6\x77\x04\x00\x00\x00\x08\x00\x00\x00\x50\xf8\x2f\x01\x40\x39\xf4\x77\xa8\x20\x14\x00\x1c\xf6\x2f\x01\x2c\x20\xf4\x77\x80\x4d\xfb\x77", 0x40, NULL ) < 0 ) {
		return -11;
	};
	if ( askScanner( dev, "\x8b\x00\x03\xc8\x3a\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\x6c\x6c\x40\x40\x40\x40\x40\x40\x40\x40\x40\x40\x6c\x00\x00\x00\x00\x00\x60\x62\x62\x62\x62\x62\x51\x6c\x00\x00\x00\x00\x00\x00\x40\xf9\x2f\x01\x4f\x9d\x00\x10\x3a\x00\x00\x00\x04\xf9\x01", 0x40, NULL ) < 0 ) {
		return -12;
	};
	if ( askScanner( dev, "\x8b\x00\x04\x02\x06\x0b\x07\x13\x0e\x55\x56\x01\x44\xf8\x2f\x01\x00\x00\x00\x00\x40\x00\x00\x00\x40\x40\x40\x40\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x40\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\xc8\x01\x00\x00\xc8\x01\x00\x00\x40\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -13;
	};

	if ( askScanner( dev, "\x07\x00\x20\x00\x3a\x0e\x13\x07\x0f\x14\x07\x10\x15\x07\x12\x16\x07\x13\x17\x07\x14\x18\x07\x15\x18\x07\x16\x19\x07\x17\x1a\x07\x19\x1b\x07\x1a\x1c\x07\x1b\x1d\x07\x1c\x1e\x07\x1d\x1f\x07\x1e\x20\x07\x1f\x21\x07\x20\x22\x07\x21\x23\x07\x23\x23\x07\x24\x55", 0x00, NULL ) < 0 ) {
		return -14;
	};
	if ( askScanner( dev, "\x07\x00\x20\x3a\x26\x24\x07\x25\x25\x07\x26\x25\x07\x27\x26\x07\x28\x27\x07\x29\x27\x07\x2a\x28\x07\x2b\x29\x07\x2d\x29\x07\x2e\x2a\x07\x2f\x2b\x07\x30\x2b\x07\x31\x2c\x07\x07\x1d\x1f\x07\x1e\x20\x07\x1f\x21\x07\x20\x22\x07\x21\x23\x07\x23\x23\x07\x24\x55", 0x00, NULL ) < 0 ) {
		return -15;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x06\x0e\x81\x0e\x81\x09\x4d\x00\x07\x00\x20\x3a\x26\x24\x07\x25\x25\x07\x26\x25\x07\x27\x26\x07\x28\x27\x07\x29\x27\x07\x2a\x28\x07\x2b\x29\x07\x2d\x29\x07\x2e\x2a\x07\x2f\x2b\x07\x30\x2b\x07\x31\x2c\x07\x07\x1d\x1f\x07\x1e\x20\x07\x1f\x21", 0x00, NULL ) < 0 ) {
		return -16;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x3a\x00\x01\x02\x04\x05\x06\x07\x08\x09\x0a\x0b\x0c\x0d\x0e\x0f\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x1a\x1b\x1c\x1d\x1f\x20\x21\x22\x23\x24\x27\x28\x29\x2a\x2b\x2c\x2d\x2e\x2f\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x3a\x3d\x3f\xff\x00", 0x40, NULL ) < 0 ) {
		return -17;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\x0e\x85\x36\xd8\xf8\x2f\x01\xf1\x9d\x00\x10\xf8\xf8\x2f\x01\x99\xf8\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x9e\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -18;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\x0d\x00\x00\x02\x9e\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\xec\xf8\x2f\x01\x97\x40\x01\x10\x03\x00\x00\x00\x00\x00\x00\x00\xfa\x45\x03\x10\x02\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -19;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf7\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\x6c\x6c\x40\x40\x40\x40\x40\x40\x40\x40\x40\x40\x6c\x00\x00\x00\x00\x00\x60\x62\x62\x62\x62\x62\x51\x6c\x00\x00\x00\x00\x00\x00\x02\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -20;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf7\x00\x00\x02\xf9\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\x6c\x6c\x40\x40\x40\x40\x40\x40\x40\x40\x40\x40\x6c\x00\x00\x00\x00\x00\x60\x62\x62\x62\x62\x62", 0x40, NULL ) < 0 ) {
		return -21;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\xf7\xf4\x00\x14\xf9\x2f\x01\x6d\x4f\x01\x10\x94\xf8\x2f\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x02\xf9\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -22;
	};
	if ( askScanner( dev, "\x03\x00\x00\x00\x02\x20\x6c\x01\x6d\x4f\x01\x10\x94\xf8\x2f\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x02\xf9\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\xe8\xf8\x2f\x01", 0x00, NULL ) < 0 ) {
		return -23;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf9\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c\xe8\xf8\x2f\x01\xec\xf8\x2f\x01\x97\x40\x01\x10\x03\x00\x00\x00\x00\x00\x00\x00\xfa\x45\x03\x10\x02\x00\x00\x00\x02\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -24;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\xf9\x01\x00\x1c\xf9\x2f\x01\x6d\x4f\x01\x10\x9c\xf8\x2f\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x02\x6c\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09\x0f\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -25;
	};
	if ( askScanner( dev, "\x03\x00\x00\x00\x12\x1c\x0c\x1b\x08\x1a\x07\x30\x08\x09\x6d\x08\x27\x00\x9e\x00\x1e\x23\x47\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x02\x6c\xbf\x85\x85\x02\x05\x26\x25\x4d\x10\x10\x00\xff\x81\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x09\x09", 0x00, NULL ) < 0 ) {
		return -26;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x3a\x00\x01\x02\x04\x05\x06\x07\x08\x09\x0a\x0b\x0c\x0d\x0e\x0f\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x1a\x1b\x1c\x1d\x1f\x20\x21\x22\x23\x24\x27\x28\x29\x2a\x2b\x2c\x2d\x2e\x2f\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x3a\x3d\x3f\xff\x00", 0x40, NULL ) < 0 ) {
		return -27;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\x0d\xff\x36\xdc\xf8\x2f\x01\xf1\x9d\x00\x10\xfc\xf8\x2f\x01\x9d\xf8\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x1e\x3f\x05\x05\x02\x05\x26\x27\x6d\x10\x10\x00\xff\x85\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x07\x08\x0c\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -28;
	};

	if ( askScanner( dev, "\x08\x00\x00\x00\x0a\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x10\xfc\xf8\x2f\x01\x9d\xf8\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x1e\x3f\x05\x05\x02\x05\x26\x27\x6d\x10\x10\x00\xff\x85\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x07\x08\x0c\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -29;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x08\x0e\x85\x09\xed\x09\x6d\x09\xed\x1e\x3f\x05\x05\x02\x05\x26\x27\x6d\x10\x10\x00\xff\x85\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x07\x08\x0c\x00\x6c\x6c\xf0\xf8\x2f\x01\x97\x40\x01\x10\x08\x00\x00\x00\x00\x00\x00\x00\x3e\xf9\x2f\x01", 0x00, NULL ) < 0 ) {
		return -30;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf3\x6c\x6c\xf0\xf8\x2f\x01\x97\x40\x01\x10\x08\x00\x00\x00\x00\x00\x00\x00\x3e\xf9\x2f\x01\x04\xf9\x2f\x01\x97\x40\x01\x10\x03\x00\x00\x00\x00\x00\x00\x00\x00\x46\x03\x10\x08\x00\x00\x00\x08\x00\x00\x00\x08\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -31;
	};

	if ( askScanner( dev, "\x84\x00\x00\x00\x32\x02\xa3\x04\x10\x3b\xa3\x04\x10\x1a\xa3\x04\x10\xf9\xa2\x04\x10\xd8\xa2\x00\xb9\x19\xe2\x87\xba\x56\x78\x72\x68\x9e\x7a\xf4\x65\x6d\xd9\xde\xf6\x33\xa2\x04\x10\x12\xa2\x04\x10\xf1\xa1\x04\x10\x04\x00\x00\x00\x00\x00\x00\xb4\x2d\x6c\xe9", 0x40, NULL ) < 0 ) {
		return -32;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x06\x1a\x07\x1b\x08\x1c\x0c\x77\x21\xac\xe5\x77\x00\x00\x00\x00\xaa\x4e\x01\x10\x3c\x01\x00\x00\xc4\xf8\x2f\x01\xdc\xf8\x2f\x01\x00\x00\x00\x00\x40\x00\x00\x00\xb9\x19\xe2\x87\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x40\x00\x00\x00", 0x00, NULL ) < 0 ) {
		return -33;
	};

	if ( askScanner( dev, "\x08\x00\x00\x00\x0a\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x00\x40\x00\x00\x00\x00\x00\x00\x00\x40\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\xc8\x01\x00\x00\x40\x00\x00\x00\x01\x00\x00\x00\xcc\xf8\x2f\x01\x8b\x41\x01\x10\x8c\xf8\x2f\x01\x40\x00\x00\x00", 0x00, NULL ) < 0 ) {
		return -34;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x04\x3d\x51\x0a\x00\x01\x00\x00\x01\x00\x00\x00\x01\x00\x00\x00\x00\x00\x00\x00\xfc\xf9\x2f\x01\x31\x10\x01\x10\xd0\xf9\x2f\x01\x00\x00\x00\x00\x1a\x07\x1b\x08\x1c\x0c\xc6\xf8\x66\xbc\xc4\xbe\x0b\x25\xc5\x4c\xf4\x03\x10\x2f\x11\x3f\x12\x44", 0x00, NULL ) < 0 ) {
		return -35;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x3a\x00\x01\x02\x04\x05\x06\x07\x08\x09\x0a\x0b\x0c\x0d\x0e\x0f\x10\x11\x12\x13\x14\x15\x16\x17\x18\x19\x1a\x1b\x1c\x1d\x1f\x20\x21\x22\x23\x24\x27\x28\x29\x2a\x2b\x2c\x2d\x2e\x2f\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x3a\x3d\x3f\xff\x00", 0x40, NULL ) < 0 ) {
		return -36;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\x0a\x10\x36\x88\xf9\x2f\x01\xf1\x9d\x00\x10\xa8\xf9\x2f\x01\x49\xf9\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x1e\x3f\x05\x05\x02\x05\x26\x27\xed\x00\x10\x00\xff\x85\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x07\x08\x0c\x00\x6c\x6c", 0x00, NULL ) < 0 ) {
		return -37;
	};
	if ( askScanner( dev, "\x8b\x00\x00\xbc\x3a\x40\xd3\x60\x00\x00\x00\x00\x00\x00\x00\x00\x00\xd8\xf4\x2f\x01\x80\x69\x67\xff\xff\xff\xff\xff\x00\xf0\xfd\x7f\x00\x60\xfd\x7f\x3c\x01\x00\x00\xa0\xf5\x2f\x01\x03\x01\x00\x00\x9a\x11\xf4\x77\x9f\x11\xf4\x77\x3c\x01\x00\x00\xa0\xf5\x01", 0x40, NULL ) < 0 ) {
		return -38;
	};
	if ( askScanner( dev, "\x8b\x00\x00\xf6\x3a\x0b\x07\xa5\x03\x2f\x63\x97\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -39;
	};
	if ( askScanner( dev, "\x8b\x00\x01\x30\x3a\x0b\x00\x00\x00\x00\x00\x00\x12\xcd\xa6\x3c\x36\xec\x6a\x73\x00\x64\x75\xdf\x2e\x13\xec\xca\x3c\x03\x00\x00\x06\xa5\x00\x01\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -40;
	};
	if ( askScanner( dev, "\x8b\x00\x01\x6a\x3a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -41;
	};
	if ( askScanner( dev, "\x8b\x00\x01\xa4\x3a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x06\xa5\x83\x1b\x8e\xac\x00\x00\x0b\xa5\x08\x08\x03\x00\x00\x01\x02\x03\x06\x00\x00\x00\x00\x00\x8d\xa5\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -42;
	};
	if ( askScanner( dev, "\x8b\x00\x01\xde\x3a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -43;
	};
	if ( askScanner( dev, "\x8b\x00\x02\x18\x3a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -44;
	};
	if ( askScanner( dev, "\x8b\x00\x02\x52\x3a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -45;
	};
	if ( askScanner( dev, "\x8b\x00\x02\x8c\x3a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -46;
	};
	if ( askScanner( dev, "\x8b\x00\x02\xc6\x2a\x0b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\xc8\x01\x00\x00\xc8\x01\x00\x00\x40\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -47;
	};

	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xf1\x2f\x01\x49\xf9\x2f\x01\x3a\x00\x00\x00\x00\x00\x00\x00\x02\x1e\x3f\x05\x05\x02\x05\x26\x27\xed\x00\x10\x00\xff\x85\x6c\x00\x00\xcf\x00\x01\x00\x00\x1f\x01\x01\x07\x08\x0c\x00\x6c\x6c\x9c\xf9\x2f\x01\x97\x40\x01\x10\x03\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -48;
	};

	if ( askScanner( dev, "\x03\x00\x00\x00\x02\xf1\x01\x00\xb4\xf9\x2f\x01\x6d\x4f\x01\x10\x34\xf9\x2f\x01\x40\x00\x00\x00\xe8\x03\x00\x00\x00\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x00, NULL ) < 0 ) {
		return -49;
	};
	if ( askScanner( dev, "\x8b\x00\x01\x10\x3a\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -50;
	};
	if ( askScanner( dev, "\x8b\x00\x01\x4a\x2e\x0b\x06\xa5\x00\x01\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\xc8\x01\x00\x00\xc8\x01\x00\x00\x40\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -51;
	};
	if ( askScanner( dev, "\x82\x00\x00\x00\x01\xfb\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x88\xf9\x2f\x01\x97\x40\x01\x10\x03\x00\x00\x00\x00\x00\x00\x00\xfa\x45\x03\x10\x02\x00\x00\x00\x02\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00", 0x40, NULL ) < 0 ) {
		return -51;
	};

	/* enable sensor */
	if ( askScanner( dev, anInitCommand, 0x00, NULL ) < 0 ) {
		return -52;
	}

	return 0;
}

static int DetectFinger( struct fp_img_dev *dev ) {
	sint32 nRet = 0;
	uint8 *pnData = NULL;

	pnData = g_malloc( SENSOR_FULL_IMAGE );

	nRet = askScanner( dev, anScanCommand, SENSOR_FULL_IMAGE, pnData );

	if ( nRet != SENSOR_FULL_IMAGE ) {
		nRet = 0;
		goto end;
	}

	nRet = ValidScan( pnData );

end:
	g_free( pnData );

	return nRet;
}

static int awaitFingerOn( struct fp_img_dev *dev ) {
	int nRet = 0;
	int nCount = WAIT_COUNT;

	/* wait until a finger is present */
	do {
		nRet = DetectFinger( dev );
	} while ( nRet == 0 );

	/* give user time to scan his full finger */
	while ( nCount-- ) {
		nRet = DetectFinger( dev );
	}

	return nRet != 1 ? nRet : 0;
}

static int capture( struct fp_img_dev *dev, gboolean unconditional, struct fp_img **ppsRet ) {
	struct fp_img *psImg = NULL;
	uint8 *pnData = NULL;
	sint32 nRet = 0;

	psImg = fpi_img_new_for_imgdev( dev );
	pnData = g_malloc( SENSOR_FULL_IMAGE );

	nRet = askScanner( dev, anScanCommand, SENSOR_FULL_IMAGE, pnData );
	if ( nRet == SENSOR_FULL_IMAGE ) {
		memcpy( psImg -> data, pnData, SENSOR_FULL_IMAGE );
		*ppsRet = psImg;
		nRet = 0;
	} else {
		nRet = -1;
	}

	g_free( pnData );

	return nRet;
}

static int dev_init( struct fp_img_dev *dev, unsigned long driver_data ) {
	int nResult;

	nResult = libusb_claim_interface(dev->udev, 0);
	if ( nResult < 0 ) {
		fp_err( "could not claim interface 0" );
		return nResult;
	}

	nResult = SetupSensor( dev );

	return nResult;
}

static void dev_exit( struct fp_img_dev *dev ) {
	libusb_release_interface(dev->udev, 0);
}

static const struct usb_id id_table[] = {
	{ .vendor = 0x0483, .product = 0x2015 },
	{ 0, 0, 0, },
};

struct fp_img_driver upektc_driver = {
	.driver = {
		.id = 5,
		.name = FP_COMPONENT,
		.full_name = "UPEK TouchChip",
		.id_table = id_table,
		.scan_type = FP_SCAN_TYPE_PRESS,
	},
	.flags = FP_IMGDRV_SUPPORTS_UNCONDITIONAL_CAPTURE,
	.img_height = 288,
	.img_width = 208,

	.bz3_threshold = 30,
	.init = dev_init,
	.exit = dev_exit,
	.await_finger_on = awaitFingerOn,
	.capture = capture,
};
