package nostro;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
    STUDENTI:

    nome,cognome,facolta,id

    CLASSI:
    
*/
public class EsercizioStudenti {
    public static void main(String[] args) throws IOException {
      

        //da testo gli alunni
        String testoAlunni = Files.readString(Paths.get("studenti.txt"));
        String[] righeAlunni = testoAlunni.split("\n");

        Studente[] studenti = new Studente[righeAlunni.length]; //dichiaro vettore studenti
        
        for(int i = 0; i<righeAlunni.length && i<studenti.length; i++){
            String[] riga = righeAlunni[i].split(","); //[nome,cognome,facolta,id]
            studenti[i] = new Studente(riga[0],riga[1],riga[2],Integer.parseInt(riga[3]));
        }

        //da testo le aule
        String testoAule = Files.readString(Paths.get("aule.txt"));
        String[] righeAule = testoAule.split("\n");

        Aula[] aule = new Aula[righeAule.length];   //dichiaro vettore aule
   
        for(int i = 0; i<righeAule.length && i<aule.length; i++){
            String[] riga = righeAule[i].split(",");
            aule[i] = new Aula(
                Integer.parseInt(riga[0]),
                Integer.parseInt(riga[1]),
                riga[2]
            );
        }

        //assegno gli studenti alle loro aule
        for(int i=0;i<studenti.length;i++){
            for(int j=0;j<aule.length; j++){
                if(studenti[i].getFacolta().equals(aule[j].getCorso())){
                  aule[j].aggiungiStudente(studenti[i]);
                } 
            }
        }
        for(int i=0;i<aule.length;i++){
            System.out.println(aule[i]);
            System.out.println("_______________________");
        }
    }

    public static void stampaClassi(Aula[] classi){
        stampaClassi(classi,0);
    }
    
    public static void stampaClassi(Aula[] classi, int i) {
        if (i < classi.length) {
            System.out.println(classi[i]);
            System.out.println("-----------------");
            i++;
            stampaClassi(classi, i);
        }
    }

}

class Studente {
    private String nome;
    private String cognome;
    private String facolta;
    private int id;

    public Studente(String nome, String cognome, String facolta, int id) {
        this.nome = nome;
        this.cognome = cognome;
        this.facolta = facolta;
        this.id = id;
    }

    public String toString() {
        return "\n\tNome: " + this.nome + "\n\tCognome: " + this.cognome + "\n\tFacoltà: " + this.facolta + "\n\tId: "
                + this.id + "\n";
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public int getId() {
        return this.id;
    }

    public String getFacolta() {
        return this.facolta;
    }

}

class Aula {
    int idClasse, capienza;
    String corso;
    Vector<Studente> studenti = new Vector<Studente>();

    public Aula(int numClasse, int capienza, String corso) {
        this.idClasse = numClasse;
        this.capienza = capienza;
        this.corso=corso;
    }

    public void aggiungiStudente(Studente studente){
        //TODO se ci sono piu studenti di quanto è la capacità, crea un errore
        this.studenti.add(studente);
    }
    
    public int getcapienza() { 
        return this.capienza;
    }

    public int getClasse() { 
        return this.idClasse;
    }

    public String getCorso() { 
        return corso;
    }

    public String toString(){  
        int numStudenti = studenti.size();
        String output = 
            "Classe: "+idClasse+"\n"+
            "Indirizzo: "+corso+"\n"+
            "Capienza: "+capienza+"\n"+
            "Posti liberi: "+(capienza-numStudenti)+"\n\n"+
            "---Alunni---\n";
        for(int i=0;i<numStudenti;i++){
            output+=studenti.get(i);
        }
        return output; 
    }
    public void stampaStudenti(){
        stampaStudenti(studenti,0);
    }

    public void stampaStudenti(Vector<Studente> arrStud, int i) {
        if (i < studenti.size()) {
            System.out.print((i+1)+") "); 
            System.out.println(arrStud.get(i));
            i++;
            stampaStudenti(arrStud, i);
        }
    }
}




