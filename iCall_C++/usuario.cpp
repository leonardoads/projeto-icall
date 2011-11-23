#include "usuario.h"
#include <string>
#include <iostream>

string Usuario::getNome(){
       return nome;
}
string Usuario::getMatricula(){
       return matricula;
}
void Usuario::setNome(string nome){
       this->nome = nome;
}
void Usuario::setMatricula(string matricula){
       this->matricula = matricula;
}
