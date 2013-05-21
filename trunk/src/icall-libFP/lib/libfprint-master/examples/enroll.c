/* Desenvolvido por Felipe da Silva Travassos
 * com base em exemplos da libfprint 1.6
 */
 

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <libfprint/fprint.h>


//Metodo para encontrar e retorna o dispositivo
struct fp_dscv_dev *discover_device(struct fp_dscv_dev **discovered_devs)
{
	struct fp_dscv_dev *dispositivo = discovered_devs[0];
	struct fp_driver *drv;
	if (!dispositivo)
		return NULL;
	
	drv = fp_dscv_dev_get_driver(dispositivo);
	return dispositivo;
}

struct fp_print_data *enroll(struct fp_dev *dev) {
	struct fp_print_data *enrolled_print = NULL;
	int r;

	system("zenity --info --title='Sucesso' --text='Passe o dedo indicador da mão direita, apos concluido \no cadastro, aparecera uma mensagem informando'");
	do {
		struct fp_img *img = NULL;

		r = fp_enroll_finger_img(dev, &enrolled_print, &img);
		if (img) {
			fp_img_save_to_file(img, "enrolled.pgm");
			fp_img_free(img);
		}
		if (r < 0) {
			system("zenity --info --title='Erro' --text='Cadastro deu erro!'");
			return NULL;
		}

		switch (r) {
		case FP_ENROLL_COMPLETE:
			system("zenity --info --title='Sucesso' --text='Enroll completo!'");
			break;
		case FP_ENROLL_FAIL:
			system("zenity --warning --title='Falha' --text='Erro na cadastramento :('");
			return NULL;
		case FP_ENROLL_PASS:
			system("zenity --info --title='Sucesso' --text='OK passe seu dedo novamente!'");
			break;
		case FP_ENROLL_RETRY:
			system("zenity --warning --title='Falha' --text='Tente novamente.'");
			break;
		case FP_ENROLL_RETRY_TOO_SHORT:
			system("zenity --warning --title='Falha' --text='Tente novamente, passando uma área maior do dedo ;/");
			break;
		case FP_ENROLL_RETRY_CENTER_FINGER:
			system("zenity --warning --title='Falha' --text='Tente novamente ;/'");
			break;
		case FP_ENROLL_RETRY_REMOVE_FINGER:
			system("zenity --warning --title='Falha' --text='Remova o dedo do dispositivo e tente novamente'");
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
	struct fp_dscv_dev *dispositivo;
	struct fp_dscv_dev **discovered_devs;
	struct fp_dev *dev;
	struct fp_print_data *data;
	
    if(1){
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

		dispositivo = discover_device(discovered_devs);
		if (!dispositivo) {
			fprintf(stderr, "Não foi detectado um dispositivo.\n");
			goto out;
		}

		dev = fp_dev_open(dispositivo);
		fp_dscv_devs_free(discovered_devs);
		if (!dev) {
			fprintf(stderr, "Não foi possível abrir o dispositivoCould not open device.\n");
			goto out;
		}
		data = enroll(dev);
		if (!data)
			goto out_close;

		r = fp_print_data_save(data, RIGHT_INDEX);
		if (r < 0){
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

