  #include "DHT.h"  //Bibliothèque


  #define DHTPIN 2     // Le pin auquel nou ssommes connectés
  #define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
  DHT dht(DHTPIN, DHTTYPE);
 
  float r =0; //Notre résistance
  float tension = 0; //La tension principale
  int R2 = 0; //La valeur de la résistance
  float vEnDegres =0;

//Coefficients Steinhart–Hart
  float A = 0.00109613;
  float B = 0.000240164;
  float C = 5.87433*pow(10,-8);

void setup() {
  Serial.begin(9600); //Initialisation de la fréquence
  dht.begin();9 //Démarrer récolte du dht
}

void loop() {
  float h = dht.readHumidity(); //Humidité
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature(); //Température interne
  
  // Vérifier si érreur
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Echec de la lecture du DHT");
    return;
  }

  /*Température Interne + Humidité == DHT*/
  float hif = dht.computeHeatIndex(f, h);
  float hic = dht.computeHeatIndex(t, h, false);
  Serial.print(h);
  Serial.print(" ");
  Serial.print(t);

  /*Température Externe -> Thermistance*/
  digitalWrite(0,HIGH); //On active le port A0
  r = analogRead(A0); //Lecture de la resistance sur le port A0
  //Calculs pour récupérer la température
  tension = 5*r/1024; // Tension U, on obtient 5V, on divise par 1024 car c'est un potentiomètre
  R2 = (tension*10000)/(5-tension); // Tension R2 (Sonde PT100)
  //1/T=A + B*ln(R) + C*ln(R)³
  vEnDegres = (float) 1/(A + B*log(R2) + C*pow(log(R2),3)) - 273.15;
  Serial.print(" ");
  Serial.println(vEnDegres);

   float vConsigne = 0; //La valeur qu'on va recevoir
   int cardispo = 0; //variable contenant le nombre de caractère disponibles dans le buffer
    
    cardispo = Serial.available();
    
    while(cardispo > 0) //tant qu'il y a des caractères à lire
    {
        vConsigne = Serial.read(); //on lit le float
        cardispo = Serial.available(); //on relit le nombre de caractères dispo
        if (vConsigne > t){
           digitalWrite(8,HIGH);
        } else{
          digitalWrite(8,LOW);
        }
    }
    //fin du programme
  delay(5000);
}
