#ifndef USUARIO_H
#define USUARIO_H
#include <iostream>
#include <string>
using std::string;

class Usuario{
      private:
              string nome;
              string matricula;
              
      public:
      Usuario(){}
             string getNome();
             string getMatricula();
             void setNome(string nome );
             void setMatricula(string matricula);
};

#endif
