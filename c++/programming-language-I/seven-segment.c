//Samuel - Obs: Estou usando o simulador do Thinkercad onde o led de 7 segmentos é do tipo ânodo comum, 
//se for cátodo comum terá que inverter os valores (0 vira 1, 1 vira 0)

#define PIN_A 2
#define PIN_B 3
#define PIN_C 4
#define PIN_D 5
#define PIN_E 6
#define PIN_F 7
#define PIN_G 8
#define PIN_H 9

uint16_t TimeInSeconds;

const byte tab_7Seg[22][7] = {
  // a b c d e f g
  {0, 0, 0, 0, 0, 1, 0}, // a
  {1, 1, 0, 0, 0, 0, 0}, // b
  {1, 1, 1, 0, 0, 1, 0}, // c
  {1, 0, 0, 0, 0, 1, 0}, // d
  {0, 0, 1, 0, 0, 0, 0}, // e
  {0, 1, 1, 1, 0, 0, 0}, // f
  {0, 0, 0, 0, 1, 0, 0}, // g
  {1, 1, 0, 1, 0, 0, 0}, // h
  {1, 0, 0, 1, 1, 1, 1}, // i
  {1, 0, 0, 0, 0, 1, 1}, // j
  {1, 0, 0, 1, 0, 0, 0}, // k
  {1, 1, 1, 1, 0, 0, 1}, // l
  {0, 1, 1, 0, 0, 0, 0}, // m - horizontal
  {1, 1, 0, 1, 0, 1, 0}, // n - horizontal
  {1, 1, 0, 0, 0, 1, 0}, // o
  {0, 0, 1, 1, 0, 0, 0}, // p
  {0, 0, 0, 1, 1, 0, 0}, // q
  {0, 1, 1, 1, 0, 0, 1}, // r
  {0, 1, 0, 0, 1, 0, 0}, // s
  // T so com 2 leds
  {1, 1, 0, 0, 0, 1, 1}, // u
  {1, 0, 0, 0, 0, 0, 1}, // v
  // W so com 2 leds
  // x so com 2 leds
  // y so com 2 leds
  {0, 0, 1, 0, 0, 1, 0} // z
};

uint16_t Value;
uint8_t pin;
byte count = 0;

void ValueConvert(byte Value){
  byte pin = 2;
  for (byte x = 0; x < 7; x++){
      digitalWrite(pin, tab_7Seg[Value][x]);
      pin++;
  }
}

void setup(){
    pinMode(PIN_A, OUTPUT);
    pinMode(PIN_B, OUTPUT);
    pinMode(PIN_C, OUTPUT);
    pinMode(PIN_D, OUTPUT);
    pinMode(PIN_E, OUTPUT);
    pinMode(PIN_F, OUTPUT);
    pinMode(PIN_G, OUTPUT);
    pinMode(PIN_H, OUTPUT);
}

void loop(){
    ValueConvert(count);
    if (count < 22){
      count++;
    }
    else{
        count = 0;
    }

    delay(2000);
}