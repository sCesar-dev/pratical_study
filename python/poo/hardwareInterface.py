#!/usr/bin/env python3

from servoMotor import ServoMotor

class HardwareInterface():

    def __init__(self):
        self.connected_servos = {}
    
    def register_servo(self, servo_object:ServoMotor):
        self.connected_servos[servo_object.id] = servo_object

    def get_servo_by_id(self, motor_id):
        motor_id = self.connected_servos.get(motor_id)
        return motor_id
    
    def get_servo_by_name(self, name):
        motor_name = self.connected_servos.get(name)
        return motor_name

if __name__ == "__main__":
    print("--- Testing HardwareInterface Class ---")
    hw_interface = HardwareInterface()

    # 1. Create some servo objects to test with
    servo1 = ServoMotor(motor_id=1, name="shoulder")
    servo2 = ServoMotor(motor_id=2, name="elbow")

    # 2. Test the register_servo method
    hw_interface.register_servo(servo1)
    hw_interface.register_servo(servo2)
    print(f"Registered servos: {hw_interface.connected_servos}")
    assert len(hw_interface.connected_servos) == 2

    # 3. Test the get_servo_by_id method
    retrieved_servo = hw_interface.get_servo_by_id(2)
    print(f"\nRetrieved servo by ID 2: {retrieved_servo}")
    assert retrieved_servo.name == "elbow"

    # 4. Test the get_servo_by_name method
    retrieved_servo_by_name = hw_interface.get_servo_by_name("shoulder")
    print(f"\nRetrieved servo by name 'shoulder': {retrieved_servo_by_name}")
    assert retrieved_servo_by_name.id == 1

    print("HardwareInterface tests passed! ✅")
