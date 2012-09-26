/*
 * Example fingerprint enrollment program
 * Enrolls your right index finger and saves the print to disk
 * Copyright (C) 2007 Daniel Drake <dsd@gentoo.org>
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

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <libfprint/fprint.h>

struct fp_dscv_dev *discover_device(struct fp_dscv_dev **discovered_devs)
{
	struct fp_dscv_dev *ddev = discovered_devs[0];
	struct fp_driver *drv;
	if (!ddev)
		return NULL;
	
	drv = fp_dscv_dev_get_driver(ddev);
	//printf("Found device claimed by %s driver\n", fp_driver_get_full_name(drv));
	return ddev;
}

struct fp_print_data *enroll(struct fp_dev *dev) {
	struct fp_print_data *enrolled_print = NULL;
	int r;

	//system("dialog --infobox 'Dispositivo encontrado, passe o seu dedo indicador da mão"
	// "direita no leitor "" vezes' 5 30");
	
	//printf("You will need to successfully scan your finger %d times to "
	//	"complete the process.\n", fp_dev_get_nr_enroll_stages(dev));
	system("dialog --infobox 'Passe o dedo indicador da mão direita, apos concluido o cadastro, aparecera uma mensagem informando' 10 30");
	do {
		struct fp_img *img = NULL;
	
		sleep(1);

		r = fp_enroll_finger_img(dev, &enrolled_print, &img);
		if (img) {
			fp_img_save_to_file(img, "enrolled.pgm");
			system("dialog --infobox 'Escreveu imagem digitalizada para enrolled.pgm' 5 30");
			fp_img_free(img);
		}
		if (r < 0) {
			printf("Enrrol deu erro %d\n", r);
			return NULL;
		}

		switch (r) {
		case FP_ENROLL_COMPLETE:
			system("dialog --infobox 'Enroll completo!' 5 30");
			break;
		case FP_ENROLL_FAIL:
			system("dialog --infobox 'Enroll failed, something wen't wrong :(' 10 30");
			return NULL;
		case FP_ENROLL_PASS:
			system("dialog --infobox 'OK passe seu dedo novamente!' 5 30");
			break;
		case FP_ENROLL_RETRY:
			system("dialog --infobox 'Didn't quite catch that. Please try again.' 10 30");
			break;
		case FP_ENROLL_RETRY_TOO_SHORT:
			system("dialog --infobox 'Your swipe was too short, please try again.' 5 30");
			break;
		case FP_ENROLL_RETRY_CENTER_FINGER:
			system("dialog --infobox 'Didn't catch that, please center your finger on the "
				"sensor and try again.' 7 30");
			break;
		case FP_ENROLL_RETRY_REMOVE_FINGER:
			system("dialog --infobox 'Scan failed, please remove your finger and then try "
				"again.' 5 30");
			break;
		}
	} while (r != FP_ENROLL_COMPLETE);

	if (!enrolled_print) {
		system("dialog --infobox 'Enroll complete but no print?' 5 30");
		return NULL;
	}
	system("dialog --infobox 'Enrollment completed!' 5 30");
	return enrolled_print;
}

int main(void)
{
	int r = 1;
	struct fp_dscv_dev *ddev;
	struct fp_dscv_dev **discovered_devs;
	struct fp_dev *dev;
	struct fp_print_data *data;
	
	system("dialog --infobox 'Precione enter para continuar' 5 30");
	getchar();

	r = fp_init();
	if (r < 0) {
		fprintf(stderr, "Falha na inicialização da libfprint\n");
		exit(1);
	}
	fp_set_debug(3);

	discovered_devs = fp_discover_devs();
	if (!discovered_devs) {
		fprintf(stderr, "Não foi possível descobrir dispositivos\n");
		goto out;
	}

	ddev = discover_device(discovered_devs);
	if (!ddev) {
		fprintf(stderr, "Não foi detectado um dispositivo.\n");
		goto out;
	}

	dev = fp_dev_open(ddev);
	fp_dscv_devs_free(discovered_devs);
	if (!dev) {
		fprintf(stderr, "Não foi possível abrir o dispositivoCould not open device.\n");
		goto out;
	}

	system("dialog --infobox 'Inaugurado dispositivo. É agora tempo de inscrever o seu dedo' 5 30");
	data = enroll(dev);
	if (!data)
		goto out_close;

	r = fp_print_data_save(data, RIGHT_INDEX);
	if (r < 0)
		fprintf(stderr, "Falha ao salvar dados, codigo %d\n", r);

	fp_print_data_free(data);
out_close:
	fp_dev_close(dev);
out:
	fp_exit();
	return r;
}


