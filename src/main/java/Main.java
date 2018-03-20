public class Main {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.add("cucc", "valami");
        hashMap.add("valami", "cucc");
        System.out.println(hashMap.getValue("cucc"));
        hashMap.remove("cucc");
        System.out.println(hashMap.getValue("cucc"));
        hashMap.clearAll();
        System.out.println(hashMap.getValue("valami"));

    }
}
