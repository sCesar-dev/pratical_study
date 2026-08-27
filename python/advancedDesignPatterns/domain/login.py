class Login:
    def __init__(self, nome, senha):
        self.nome = nome
        self.senha = senha

    def getNome(self):
        return self.nome

    def getSenha(self):
        return self.senha

    def verificaLogin(self, nome, senha):
        return True if self.nome is nome and self.senha is senha else False


class LoginEspecial(Login):
    def __init__(self, nome, senha, dica):
        super().__init__(nome, senha)
        self.dica = dica

    def getDica(self):
        return self.dica

if __name__ == "__main__":

    login = Login("eduardo", "123")
    print(login.verificaLogin("carlos","123"))

    loginEspecial = LoginEspecial("fulano", "123", "alguem")
    print(loginEspecial.getDica())