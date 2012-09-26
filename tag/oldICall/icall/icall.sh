menu(){
	clear
	echo "---------------------------------------------------------------------"
	echo "    ICALL - sIstema biometriCo para o registro do ALuno em saLa      "
	echo " ******************************************************************  "
	echo "       MENU"
	echo "       1 - CADASTRAMENTO"
	echo "       2 - VERIFICAÇÃO"
	echo "       3 - GERAÇÃO DE RELATORIO"
	echo "       4 - SAIR"
	echo "Digite a opção desejada: "
	read opcao
	case $opcao in
	1) enroll ;;
	2) verify ;;
	3) gerar_relatorio ;;
	4) echo "Finalizando o Sistema"; exit;;
	*) echo "Insira outro código";
	menu ;;
	esac
}

enroll()
{
	clear
	echo "Digite nome"
	read nome
	
	echo "Digite sua matricula"
	read matricula
	
	./enroll
	
	mkdir -p  ~/.icall/$matricula
	cp -p  ~/.fprint/prints/0001/00000000/7 ~/.icall/$matricula && echo $matricula"|"$nome >> enroll.icall  && clear && echo "Cadastramento Realizado com sucesso" ||  echo "Desculpe-nos mas ocorreu um erro inesperado" 
	sleep 22s
	
	menu 
}

verify()
{
	echo "Digite a sua matricula"
	read matricula
	
	verifica=1
	
	cp -p ~/.icall/$matricula/7 ~/.fprint/prints/0001/00000000 || echo "matricula ainda não cadastrada"  && $verifica=0
	arq=match.icall
	cat $arq
	if [ $verifica != 0 ]
		then
			./verify
			
	fi
	read linha < $arq
	echo $linha
	if [ $linha != "match" ]
	then
		echo oi
	else
		echo $matricula >> verify.icall
	fi
	
	menu

}	
	
gerar_relatorio()
{
	clear
	python gera_relatorio.py
	echo "-------------------------------Relatorio-------------------------------------"
	cat -b relatorio.icall
	
	echo "\n"
	echo "pressione enter para sair"
	read sair
	menu
}
menu	
	
	
	
	
	
	
	
	
	
	
	
