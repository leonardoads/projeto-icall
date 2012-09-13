#include <iostream>
#include "usuario.cpp"

using namespace std;

int main()
{
  Usuario usuario;
  usuario.setNome("Felipe");
  usuario.setMatricula("2102-1386");
  cout << "Nome: " << usuario.getNome() << "\n" << "Matricula: " << usuario.getMatricula() <<endl;  
  return 0;
}
