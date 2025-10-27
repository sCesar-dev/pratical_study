#!/usr/bin/env python3

class ServoMotor:

    def __init__(self, motor_id, name):
        self.id = motor_id
        self.name = name
        self.current_position = 512
        self.goal_position = 512
        self.is_torque_enabled = True

    def __repr__(self):
        return(f"\nSERVO ID: {self.id} \nSERVO NAME: {self.name} \nCURRENT POSITION: {self.current_position} \nGOAL POSITION: {self.goal_position} \nTorque status: {self.is_torque_enabled}")


if __name__ == "__main__":
    servo1 = ServoMotor(motor_id=1, name="shoulder_pan_joint")
    servo2 = ServoMotor(motor_id=2, name="shoulder_lift_joint")
    print("Testing ServoMotor class:")
    print(servo1)
    print(servo2)