#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
#       sem título.py
#       
#       Copyright 2011 Leonardo <leonardo@zezinho-eME443>
#       
#       This program is free software; you can redistribute it and/or modify
#       it under the terms of the GNU General Public License as published by
#       the Free Software Foundation; either version 2 of the License, or
#       (at your option) any later version.
#       
#       This program is distributed in the hope that it will be useful,
#       but WITHOUT ANY WARRANTY; without even the implied warranty of
#       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#       GNU General Public License for more details.
#       
#       You should have received a copy of the GNU General Public License./verify
#       along with this program; if not, write to the Free Software
#       Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
#       MA 02110-1301, USA.
#       
#       



def main():
	dict_matricula_presenca = {}
	dict_matricula_nome = {}
	try:
		file_enroll = open("enroll.icall", "r")
	except:
		raise "Arquivo não encontrado. Verifique se já houve algum cadastramento."
		exit()
		
	try:
		file_verify = open("verify.icall", "r")
	except:
		raise "Arquivo nao encontrado. Verifique se já houve alguma verificação"
		exit()
	
	try:
		relatorio = open("relatorio.icall", "w")
	except:
		raise "Houve problemas na criação do relatorio"
		exit()
		
	lista_cadastrados = file_enroll.readlines()
	lista_verificados = file_verify.readlines()
	
	for line in lista_cadastrados:
		line_split =  line.split("|")
		dict_matricula_presenca[line_split[0]] = 0
		dict_matricula_nome[line_split[0]] = line_split[1].split("\n")[0]
	
	for line in lista_verificados:
		line_split = line.split("\n")
		try:
			dict_matricula_presenca[line_split[0]]=1
		except:
			print "erro ao verificar presenca"
		
			
	for key in dict_matricula_nome:
		relatorio.write(key+"|"+dict_matricula_nome[key]+"|"+str(dict_matricula_presenca[key])+"\n")
		
	relatorio.close()

if __name__ == '__main__':
	main()

