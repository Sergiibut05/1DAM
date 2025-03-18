package serializer;

public interface Serializer {
    // This class is used to serialize the Pasajero class
    public  String serialize();
    // This class is used to deserialize the Pasajero class
    public  void deserialize(String t);
}
