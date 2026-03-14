from abc import ABC, abstractmethod

class Action(ABC):

    def __init__(self):
        self.__progress = 0.0
        self.__state = "WAITING"

    @property
    def progress(self):
        return self.progress
    
    @progress.setter
    def progress(self, progress):
        if progress <= 0.0:
            self.__progress = 0.0
        elif progress >= 100.0:
            self.__progress = 100.0
            self.__state = "FINISHED"
        else:
            self.__progress = progress
            self.__state = "RUNNING"

    @property
    def estado(self):
        return self.state
    
    @abstractmethod
    def cicle_execute(self) -> bool:
        """
        Childreen logical here.
        """
        raise NotImplementedError("Method cicle_execute need to be implemented")
