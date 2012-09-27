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
	system("zenity --info --title='Sucesso' --text='Passe o dedo indicador da mão direita, apos concluido o cadastro, aparecera uma mensagem informando'");
	do {
		struct fp_img *img = NULL;
	
		sleep(1);

		r = fp_enroll_finger_img(dev, &enrolled_print, &img);
		if (img) {
			fp_img_save_to_file(img, "enrolled.pgm");
			system("zenity --info --title='Sucesso' --text='Escreveu imagem digitalizada para enrolled.pgm'");
			fp_img_free(img);
		}
		if (r < 0) {
			printf("Enrrol deu erro %d\n", r);
			return NULL;
		}

		switch (r) {
		case FP_ENROLL_COMPLETE:
			system("zenity --info --title='Sucesso' --text='Enroll completo!'");
			break;
		case FP_ENROLL_FAIL:
			system("zenity --warning --title='Falha' --text='Enroll failed, something wen't wrong :('");
			return NULL;
		case FP_ENROLL_PASS:
			system("zenity --info --title='Sucesso' --text='OK passe seu dedo novamente!'");
			break;
		case FP_ENROLL_RETRY:
			system("zenity --warning --title='Falha' --text='Didn't quite catch that. Please try again.'");
			break;
		case FP_ENROLL_RETRY_TOO_SHORT:
			system("zenity --warning --title='Falha' --text='Your swipe was too short, please try again.'");
			break;
		case FP_ENROLL_RETRY_CENTER_FINGER:
			system("zenity --warning --title='Falha' --text='Didn't catch that, please center your finger on the "
				"sensor and try again.'");
			break;
		case FP_ENROLL_RETRY_REMOVE_FINGER:
			system("zenity --warning --title='Falha' --text='Scan failed, please remove your finger and then try "
				"again.'");
			break;
		}
	} while (r != FP_ENROLL_COMPLETE);

	if (!enrolled_print) {
		system("dialog --infobox 'Enroll complete but no print?' 5 30");
		return NULL;
	}
	system("zenity --info --title='Sucesso' --text='Cadastro completo'");
	return enrolled_print;
}

int main(void)
{
	int r = 1;
	struct fp_dscv_dev *ddev;
	struct fp_dscv_dev **discovered_devs;
	struct fp_dev *dev;
	struct fp_print_data *data;
	
    if(!system("zenity --question --title='Cadastramento' --text='Continuar?'")){
		r = fp_init();
		if (r < 0) {
			system("zenity --warning --title='Falha' --text='Falha na inicialização da libfprint'");
			fprintf(stderr, "Falha na inicialização da libfprint\n");
			exit(1);
		}
		fp_set_debug(3);

		discovered_devs = fp_discover_devs();
		if (!discovered_devs) {
			system("zenity --warning --title='Falha' --text='Não foi possível descobrir dispositivos'");
			fprintf(stderr, "Não foi possível descobrir dispositivos\n");
			goto out;
		}

		ddev = discover_device(discovered_devs);
		if (!ddev) {
			system("zenity --warning --title='Falha' --text='Não foi detectado um dispositivo'");
			fprintf(stderr, "Não foi detectado um dispositivo.\n");
			goto out;
		}

		dev = fp_dev_open(ddev);
		fp_dscv_devs_free(discovered_devs);
		if (!dev) {
			system("zenity --warning --title='Falha' --text='Não foi possível abrir o dispositivoCould not open device.'");
			fprintf(stderr, "Não foi possível abrir o dispositivoCould not open device.\n");
			goto out;
		}

		//system("dialog --infobox 'Inaugurado dispositivo. É agora tempo de inscrever o seu dedo' 5 30");
		data = enroll(dev);
		if (!data)
			goto out_close;

		r = fp_print_data_save(data, RIGHT_INDEX);
		if (r < 0){
			system("zenity --warning --title='Falha' --text='Falha ao salvar dados'");
			fprintf(stderr, "Falha ao salvar dados, codigo %d\n", r);
		}

		fp_print_data_free(data);
	out_close:
		fp_dev_close(dev);
	out:
		fp_exit();
		return r;
	}
}


