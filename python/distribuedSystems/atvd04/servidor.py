import Pyro5.api
import threading


class Processo:
    def __init__(self, id_processo, total_processo):
        self.id_processo = id_processo
        self.vetor_local[0] * total_processo
        self.lock = threading.Lock()
        
    def evento_local(self):
        with self.lock:
            self.vetor_local[self.id_processo] += 1
            
    def atualizar_no_recebimento(self, vetor_msg):
        with self.lock:
            for k in range(len(self.vetor_local)):
                self.vetor_local[k] = max(self.vetor_local[k], vetor_msg[k])
            self.evento_local()
      

def main():

    daemon = Pyro5.api.Daemon()
    uri = daemon.register(process)
    ns = Pyro5.api.locate_ns()
    ns.register("Process.P1", uri)

    thread_servidor = threading.Thread(target=daemon.requestLoop, daemon=True)
    thread_servidor.start()


if __name__ == "__main__":
    main()