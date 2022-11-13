import java.util.function.IntFunction;

public class SmartTv {
    
    boolean State = false;
    int Volume = 20, Channel = 14;

    public void tvState(){
        if(State == false){
            System.out.println("TV Desligada");
        }else{
            System.out.println("TV Ligada");
        }
    }

    public void turnOnTv(){
        if(State == false){
            System.out.println("Ligando...");
        }
        State = true; 
    }

    public void turnOffTv(){
        if(State == true){
            System.out.println("Desligando...");
        }
        State = false;
    }

    public void increaseVolume(){
        if(State == true){
            Volume = ++Volume;
            System.out.println("Volume: "+Volume);
        }
    }

    public void decreaseVolume(){
        if(State == true){
            Volume = --Volume;
            System.out.println("Volume: "+Volume);
        }else{
            System.out.println("A TV deve ser iniciada antes desta ação!");
        }
    }

    public void increaseChannel(){
        if(State == true){
            Channel = ++Channel;
            System.out.println("Canal: "+Channel);
        }else{
            System.out.println("A TV deve ser iniciada antes desta ação!");
        }
    }

    public void decreaseChannel(){
        if(State == true){
            Channel = --Channel;
            System.out.println("Canal: "+Channel);
        }else{
            System.out.println("A TV deve ser iniciada antes desta ação!");
        }
    }

}
