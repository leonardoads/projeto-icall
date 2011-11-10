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
             vector<string> usuarios;
             string evento;
             string tema;
             string apresentador;
             //Nome evento, Nome tema evento, 
             static void novoEvento(string, string, string);
             static void listaUsuarios (string);
             
             static string getNomeEvento();
             static string getNomeApresentador();
             static string getTemaEvento();
             static string getUsuarios();
             
             static void setNomeEvento(string);
             static void setNomeApresentador(string);
             static void setTemaEvento(string);
             static void setListaUsuarios (string);
             
      private:
             //usuario para arquivo usuarios ou evento para arquivo evento 
             static string lerArquivo(string);
             //usuario para arquivo usuarios ou evento para arquivo evento , conteudo, true = adicionar ou false = trocar
             static void escreveArquivo(string,string, bool);
};

void Evento::novoEvento(string nomeEvento, string temaEvento, string apresentador){
       
}

void Evento::listaUsuarios (string usuarios){
     
}

string Evento::getNomeEvento(){
  
}

string Evento::getNomeApresentador(){
       
}

string Evento::getTemaEvento(){
       
}

string Evento::getUsuarios(){
       
}

void Evento::setNomeEvento(string nomeEvento){

}

void Evento::setNomeApresentador(string nomeApresentador){

}

void Evento::setTemaEvento(string temaEvento){

}

void Evento::setListaUsuarios(string listaUsuarios){

}
     
void Evento::escreveArquivo(string arquivo, string conteudo, bool adaicionar){
     ofstream outfile;  
     
     bool arquivoValido = false;
     if(arquivo == "usuarios"){
          outfile.open("usuarios.icall");
          arquivoValido = true;
     }
     else if(arquivo == "evento"){
          outfile.open("evento.icall");
          arquivoValido = true;
     }
 
     if (outfile.is_open() && outfile.good()){ 
         outfile << conteudo << endl;             
         outfile.close();         
     }
}
string Evento::lerArquivo(string arquivo){
     ifstream arq;
     string str;
     if(arquivo == "usuarios"){
           arq.open("usuarios.icall"); 
     }
     else if(arquivo == "evento"){
           arq.open("evento.icall"); 
     }
     else{ 
           return ("Arquivo improprio ou inexistente");
     }
     if (arq.is_open() && arq.good()){
        getline(arq, str, '#');       
        arq.close();
     }
     
     return str;
}






