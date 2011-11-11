//Projeto PET Computacao UFCG - iCall
//Autor: Felipe da Silva Travassos 10/11/2011

#include <cstdlib>
#include <iostream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <fstream>
#include <vector>

using namespace std;

class Evento{
      public:             
             //Metodos publicos
             //Nome evento, Nome tema evento, Nome apresentador
             static void novoEvento(string, string, string);
             static void listaUsuarios (string);
             
             static string getNomeEvento();
             static string getNomeApresentador();
             static string getTemaEvento();
             static string getUsuarios();
             
             static void setNomeEvento(string);
             static void setNomeApresentador(string);
             static void setTemaEvento(string);
             //Recebe um vetor com o nome de todos os usuarios
             static void setListaUsuarios (vector<string>);
             
      private:
             //Variaveis privadas
             static vector<string> usuarios;
             static string evento;
             static string tema;
             static string apresentador;
             static int possicaoVector;
             //Metodos privados
             static string lerArquivo(string);
             //usuario para arquivo usuarios ou evento para arquivo evento, true = adicionar ou false = trocar
             static void escreveArquivo(string, bool);
};
//Pronto
void Evento::novoEvento(string nomeEvento, string temaEvento, string nomeApresentador){
     possicaoVector = 0;
     evento = nomeEvento;
     tema = temaEvento;
     apresentador = nomeApresentador;
     Evento::escreveArquivo("evento", true);
       
}
//Pronto
//Recebe um usuario por vez
void Evento::listaUsuarios (string usuario){
     usuarios[possicaoVector] = usuario;
     possicaoVector++;
     Evento::escreveArquivo("usuarios", true);
}
//Pronto
string Evento::getNomeEvento(){
       return evento;  
} 
//Pronto
string Evento::getNomeApresentador(){
       return apresentador;
}
//Pronto
string Evento::getTemaEvento(){
       return tema;
}
//Pronto
string Evento::getUsuarios(){
       string retorno;
       for(int i = 0; i<= possicaoVector; i++){
               retorno += usuarios[i] + "\n";
       }
       return retorno;
}
//Pronto
void Evento::setNomeEvento(string nomeEvento){
     evento = nomeEvento;
     Evento::escreveArquivo("evento", true);
}
//Pronto
void Evento::setNomeApresentador(string nomeApresentador){
     apresentador = nomeApresentador;
     Evento::escreveArquivo("evento", true);
}
//Pronto
void Evento::setTemaEvento(string temaEvento){
     tema = temaEvento;
     Evento::escreveArquivo("evento", true);
}
//Pronto
void Evento::setListaUsuarios(vector<string> listaUsuarios){
     usuarios = listaUsuarios;
     possicaoVector = 10;
     escreveArquivo("usuarios", true);
     possicaoVector = usuarios.size() -1;
}
//Pronto     
void Evento::escreveArquivo(string arquivo, bool adicionar){
     ofstream outfile;  
     string conteudo;
     
     bool escreve = false;
     if(arquivo == "usuarios"){
          if((possicaoVector+1) % 10 == 0){
                escreve = true;
                outfile.open("usuarios.icall");
                for(int i = 0; i<= possicaoVector; i++){
                        conteudo += usuarios[i];
                }
          }
     }
     else if(arquivo == "evento"){
          outfile.open("evento.icall");
          conteudo = evento + "|" + tema + "|" + apresentador;
          escreve = true;
     }
 
     if (outfile.is_open() && outfile.good() && escreve){ 
         outfile << conteudo << endl;             
         outfile.close();         
     }
}
//Pronto
string Evento::lerArquivo(string arquivo){
     ifstream arq;
     string str;
     if(arquivo == "usuarios"){
           arq.open("usuarios.icall"); 
     }
     else if(arquivo == "evento"){
           arq.open("evento.icall"); 
     }
     if (arq.is_open() && arq.good()){
        getline(arq, str, '#');       
        arq.close();
     }
     
     return str;
}






