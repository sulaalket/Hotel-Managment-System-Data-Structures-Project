import java.util.*;

public class HotelManagementSystem {
    LinkedList<Room> rooms;
    Stack<Booking> bookings;
    Queue<Guest> waitingList;
    Employee[] employees;
    Map<String, Guest> guests;

    public HotelManagementSystem() {
        rooms = new LinkedList<Room>();
        bookings = new Stack<Booking>();
        waitingList = new LinkedList<Guest>();
        employees = new Employee[10];
        guests = new HashMap<String, Guest>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room " + room.getRoomNumber() + " added to the hotel.");
    }

    public void bookRoom(Guest guest, Room room) {
        if (room.isAvailable()) {
            room.setAvailable(false);
            bookings.push(new Booking(guest, room));
            guests.put(guest.getName(), guest);
            System.out.println("Room " + room.getRoomNumber() + " booked by " + guest.getName() + ".");
        } else {
            waitingList.add(guest);
            System.out.println("Room " + room.getRoomNumber() + " is not available. " + guest.getName() + " added to waiting list.");
        }
    }

    public void checkoutRoom(Guest guest) {
        Booking booking = null;
        for (Booking b : bookings) {
            if (b.getGuest().getName().equals(guest.getName())) {
                booking = b;
                break;
            }
        }
        if (booking != null) {
            bookings.remove(booking);
            booking.getRoom().setAvailable(true);
            System.out.println("Room " + booking.getRoom().getRoomNumber() + " checked out by " + guest.getName() + ".");
        } else {
            System.out.println(guest.getName() + " does not have any booking.");
        }
        if (!waitingList.isEmpty()) {
            Guest nextGuest = waitingList.remove();
            bookRoom(nextGuest, rooms.getFirst());
        }
    }

    public void addEmployee(Employee employee, int index) {
        employees[index] = employee;
        System.out.println("Employee " + employee.getName() + " added to the hotel staff.");
    }

    public void removeEmployee(int index) {
        Employee employee = employees[index];
        employees[index] = null;
        if (employee != null) {
            System.out.println("Employee " + employee.getName() + " removed from the hotel staff.");
        }
    }

    public static void main(String[] args) {
        HotelManagementSystem system = new HotelManagementSystem();
        Room room1 = new Room(101, "Standard", true);
        Room room2 = new Room(102, "Deluxe", true);
        system.addRoom(room1);
        system.addRoom(room2);
        Guest guest1 = new Guest("John Doe", "123-456-7890");
        Guest guest2 = new Guest("Jane Smith", "987-654-3210");
        system.bookRoom(guest1, room1);
        system.bookRoom(guest2, room2);
        system.checkoutRoom(guest1);
        Employee employee1 = new Employee("Alice", "Manager");
        system.addEmployee(employee1, 0);
        system.removeEmployee(0);

        HotelManagementSystem System2=new HotelManagementSystem();
        Room room3=new Room(112,"dublex", true);
        System2.addRoom(room3);
        Guest guest3=new Guest("Alket Sula", "+3556700000");
        System2.bookRoom(guest3,room3);
        System2.checkoutRoom(guest3);
    }
}

class Room {
    private int roomNumber;
    private String roomType;
    private boolean available;

    public Room(int roomNumber, String roomType, boolean available) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Guest {
    private String name;
    private String contact;

    public Guest(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

class Booking {
    private Guest guest;
    private Room room;

    public Booking(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }
}

class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
