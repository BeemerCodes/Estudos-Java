public class User {
    public static void main(String[] args) throws Exception {
        
        SmartTv smartTv = new SmartTv();

        smartTv.tvState();
        System.out.println("Volume: " + smartTv.Volume);
        System.out.println("Canal: " + smartTv.Channel);

        smartTv.turnOnTv();
        smartTv.increaseVolume();
        smartTv.decreaseChannel();
        smartTv.tvState();


        smartTv.turnOffTv();
        smartTv.increaseVolume();
        smartTv.decreaseChannel();
        smartTv.tvState();
    }
}
