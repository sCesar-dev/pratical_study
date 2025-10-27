#!/usr/bin/env python3

from falar import SpeakSkill

class GerenciadorDeSkills:

    def __init__(self):
        self.skills = {}

    def add_skill(self, name_skill, object_skills):
        self.skills[name_skill] = object_skills
    
    def execute(self, name_skill, **kwargs):
        skill = self.skills.get(name_skill)
        if skill:
            return skill.execute(**kwargs)
        else:
            raise KeyError(f"Skill '{name_skill}' not found")
        
if __name__ == "__main__":

    skill_speak = SpeakSkill()
    manager = GerenciadorDeSkills()

    manager.add_skill("speak", skill_speak)
    manager.execute("speak", texto="Fala sendo executada", duracao_segundos=5)
