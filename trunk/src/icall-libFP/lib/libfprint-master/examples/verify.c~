/*
 * Desenvolvido por Felipe da Silva Travassos
 * Criado com base nos exemplos da libfprint 1.6
 * 
 */
 
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <libfprint/fprint.h>

//Metodo para encontrar e retorna o dispositivo
struct fp_dscv_dev *discover_device(struct fp_dscv_dev **discovered_devs){
	struct fp_dscv_dev *dispositivo = discovered_devs[0]; // obj que manipula o dispositivo
	if (!dispositivo)
		return NULL;
	
	return dispositivo;
}

//Metodo responsavel pela verificacao
int verify(struct fp_dev *dev, struct fp_print_data *data){
	int r;
	do {
		struct fp_img *img = NULL;

		system("zenity --info --title='' --text='Pressione enter e passe o dedo digitado anteriomente no dispositivo, no sentido que indica as setas'");
		
		r = fp_verify_finger_img(dev, data, &img);
		
		if (img) {
			fp_img_save_to_file(img, "verify.pgm"); //nome da img da digital
			fp_img_free(img);
		}
		if (r < 0) {
			system("zenity --info --title='Erro' --text='Falha na verificação :('");	
			return -1;
		}
		switch (r) {
		case FP_VERIFY_NO_MATCH:
			system("zenity --info --title='Erro' --text='Você não é quem diz ser :p'");		
			return 0;
		case FP_VERIFY_MATCH:
			system("zenity --info --title='uhuu' --text='Você é você, :)'");
			return 1;
		case FP_VERIFY_RETRY:
			system("zenity --info --title='Erro' --text='Scan didn't quite work. Please try again.'");	
			return 2;
		case FP_VERIFY_RETRY_TOO_SHORT:
			system("zenity --info --title='Erro' --text='Tente novamente, passando uma área maior do dedo ;/'");
			return 3;
		case FP_VERIFY_RETRY_CENTER_FINGER:
			system("zenity --info --title='Erro' --text='Tente novamente ;/'");
			return 4;
		case FP_VERIFY_RETRY_REMOVE_FINGER:
			system("zenity --info --title='Erro' --text='Remova o dedo do dispositivo e tente novamente'");
			return 5;
		}
	} while (1);
}

//Programa
int main(void)
{
	int r = 1;
	struct fp_dscv_dev *dispositivo;
	struct fp_dscv_dev **discovered_devs;
	struct fp_dev *dev;
	struct fp_print_data *data;

	r = fp_init();
	if (r < 0) {
		fprintf(stderr, "Falha na inicialização da biblioteca libfprint\n");
		exit(1);
	}
	fp_set_debug(3);

	discovered_devs = fp_discover_devs();
	if (!discovered_devs) {
		fprintf(stderr, "Could not discover devices\n");
		goto out;
	}

	dispositivo = discover_device(discovered_devs);
	if (!dispositivo) {
		fprintf(stderr, "Erro leitor");
		goto out;
	}

	dev = fp_dev_open(dispositivo);
	fp_dscv_devs_free(discovered_devs);
	if (!dev) {
		fprintf(stderr, "Não foi possível inicializar o dispositivo");
		goto out;
	}

	r = fp_print_data_load(dev, RIGHT_INDEX, &data);
	if (r != 0) {
		fprintf(stderr, "Failed to load fingerprint, error %d\n", r);
		fprintf(stderr, "Did you remember to enroll your right index finger "
			"first?\n");
		goto out_close;
	}
	do {
		return verify(dev, data);
	} while (1);
	fp_print_data_free(data);
out_close:
	fp_dev_close(dev);
out:
	fp_exit();
	return -1;
}

