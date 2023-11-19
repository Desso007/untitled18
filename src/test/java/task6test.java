

import java.net.ServerSocket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class task6test {

    public static void main(String[] args) {

        Map<Integer, String> knownPorts = getKnownPorts();

        // Выводим заголовок таблицы
        System.out.printf("%-8s%-8s%-30s\n", "Протокол", "Порт", "Сервис");

        // Проверяем каждый порт в диапазоне от 0 до 49151
        for (int port = 0; port <= 49151; port++) {
            // Проверяем открыт ли порт по протоколу TCP
            if (isPortOpen(port, "TCP")) {
                String serviceName = knownPorts.getOrDefault(port, "");
                System.out.printf("%-8s%-8d%-30s\n", "TCP", port, serviceName);
            }

            // Проверяем открыт ли порт по протоколу UDP
            if (isPortOpen(port, "UDP")) {
                String serviceName = knownPorts.getOrDefault(port, "");
                System.out.printf("%-8s%-8d%-30s\n", "UDP", port, serviceName);
            }
        }
    }

    // Метод для проверки открытости порта
    private static boolean isPortOpen(int port, String protocol) {
        try {
            if (protocol.equals("TCP")) {
                // Если протокол TCP, создаем ServerSocket для данного порта и сразу закрываем его
                new ServerSocket(port).close();
            } else if (protocol.equals("UDP")) {
                // Если протокол UDP, создаем DatagramSocket для данного порта и сразу закрываем его
                new DatagramSocket(port).close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для получения известных портов и соответствующих им сервисов
    private static Map<Integer, String> getKnownPorts() {
        Map<Integer, String> knownPorts = new HashMap<>();
        knownPorts.put(135, "EPMAP");
        knownPorts.put(137, "Служба имен NetBIOS");
        knownPorts.put(138, "Служба датаграмм NetBIOS");
        knownPorts.put(139, "Служба сеансов NetBIOS");
        knownPorts.put(445, "Microsoft-DS Active Directory");
        knownPorts.put(843, "Adobe Flash");
        knownPorts.put(1900, "Simple Service Discovery Protocol (SSDP)");
        knownPorts.put(3702, "Динамическое обнаружение веб-служб");
        knownPorts.put(5040, "");
        knownPorts.put(5050, "");
        knownPorts.put(5353, "Многоадресный DNS");
        knownPorts.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        knownPorts.put(5939, "");
        knownPorts.put(6463, "");
        knownPorts.put(6942, "");
        knownPorts.put(17500, "Dropbox");
        knownPorts.put(27017, "MongoDB");
        knownPorts.put(42420, "");

        return knownPorts;
    }
}