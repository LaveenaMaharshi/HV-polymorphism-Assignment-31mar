import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Visitor {
    private String name;
    private String contactNumber;
    private String email;

    public Visitor(String name, String contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Doctor {
    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

class Appointment {
    private Visitor visitor;
    private Doctor doctor;
    private String date;
    private String timeSlot;

    public Appointment(Visitor visitor, Doctor doctor, String date, String timeSlot) {
        this.visitor = visitor;
        this.doctor = doctor;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    // Getter and setter methods
    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}

class ClinicAppointmentManager {
    List<Visitor> visitors;
    List<Doctor> doctors;
    List<Appointment> appointments;

    public ClinicAppointmentManager() {
        visitors = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
        System.out.println("Visitor added successfully.");
    }

    public void editVisitorDetails(int index, Visitor visitor) {
        visitors.set(index, visitor);
        System.out.println("Visitor details updated successfully.");
    }

    public void viewAllVisitors() {
        if (visitors.isEmpty()) {
            System.out.println("No visitors found.");
        } else {
            System.out.println("Visitors List:");
            for (int i = 0; i < visitors.size(); i++) {
                System.out.println((i + 1) + ". " + visitors.get(i).getName());
            }
        }
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    public void editDoctorDetails(int index, Doctor doctor) {
        doctors.set(index, doctor);
        System.out.println("Doctor details updated successfully.");
    }

    public void viewAppointmentScheduleForDay(String date, boolean consolidated) {
        List<Appointment> appointmentsForDay = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                appointmentsForDay.add(appointment);
            }
        }

        if (appointmentsForDay.isEmpty()) {
            System.out.println("No appointments found for the given date.");
        } else {
            System.out.println("Appointment Schedule for " + date + ":");
            for (Appointment appointment : appointmentsForDay) {
                String doctorName = appointment.getDoctor().getName();
                String visitorName = appointment.getVisitor().getName();
                String timeSlot = appointment.getTimeSlot();
                System.out.println("Doctor: " + doctorName + ", Visitor: " + visitorName + ", Time Slot: " + timeSlot);
            }

            if (consolidated) {
                System.out.println("Consolidated report:");
                int totalAppointments = appointmentsForDay.size();
                int totalVisitors = visitors.size();
                int totalDoctors = doctors.size();
                System.out.println("Total Appointments: " + totalAppointments);
                System.out.println("Total Visitors: " + totalVisitors);
                System.out.println("Total Doctors: " + totalDoctors);
            }
        }
    }

    public void bookAppointment(Visitor visitor, Doctor doctor, String date, String timeSlot) {
        Appointment appointment = new Appointment(visitor, doctor, date, timeSlot);
        appointments.add(appointment);
        System.out.println("Appointment booked successfully.");
    }

    public void editAppointment(int index, Appointment updatedAppointment) {
        appointments.set(index, updatedAppointment);
        System.out.println("Appointment details updated successfully.");
    }

    public void cancelAppointment(int index) {
        appointments.remove(index);
        System.out.println("Appointment canceled successfully.");
    }
}

public class ClinicAppointmentSystem {
    public static void main(String[] args) {
        ClinicAppointmentManager appointmentManager = new ClinicAppointmentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAppointment Management System - Menu:");
            System.out.println("1. View list of all Visitors");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day (Individual Doctor)");
            System.out.println("5. View Appointment Schedule for a Day (Consolidated Report)");
            System.out.println("6. Book an Appointment");
            System.out.println("7. Edit/Cancel Appointment");
            System.out.println("8. Add/Edit Doctor Details");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    appointmentManager.viewAllVisitors();
                    break;
                case 2:
                    System.out.print("Enter Visitor Name: ");
                    scanner.nextLine(); // Consume the remaining newline character
                    String visitorName = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    Visitor visitor = new Visitor(visitorName, contactNumber, email);
                    appointmentManager.addVisitor(visitor);
                    break;
                case 3:
                    System.out.print("Enter Visitor Index to Edit: ");
                    int visitorIndex = scanner.nextInt() - 1;
                    if (visitorIndex >= 0 && visitorIndex < appointmentManager.visitors.size()) {
                        System.out.print("Enter Visitor Name: ");
                        scanner.nextLine(); // Consume the remaining newline character
                        String updatedVisitorName = scanner.nextLine();
                        System.out.print("Enter Contact Number: ");
                        String updatedContactNumber = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String updatedEmail = scanner.nextLine();
                        Visitor updatedVisitor = new Visitor(updatedVisitorName, updatedContactNumber, updatedEmail);
                        appointmentManager.editVisitorDetails(visitorIndex, updatedVisitor);
                    } else {
                        System.out.println("Invalid Visitor Index.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Date: ");
                    scanner.nextLine(); // Consume the remaining newline character
                    String appointmentDate = scanner.nextLine();
                    System.out.print("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    Doctor individualDoctor = null;
                    for (Doctor doctor : appointmentManager.doctors) {
                        if (doctor.getName().equals(doctorName)) {
                            individualDoctor = doctor;
                            break;
                        }
                    }
                    if (individualDoctor != null) {
                        appointmentManager.viewAppointmentScheduleForDay(appointmentDate, false);
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Date: ");
                    scanner.nextLine(); // Consume the remaining newline character
                    String consolidatedDate = scanner.nextLine();
                    appointmentManager.viewAppointmentScheduleForDay(consolidatedDate, true);
                    break;
                case 6:
                    System.out.print("Enter Visitor Index: ");
                    int bookVisitorIndex = scanner.nextInt() - 1;
                    if (bookVisitorIndex >= 0 && bookVisitorIndex < appointmentManager.visitors.size()) {
                        System.out.print("Enter Doctor Name: ");
                        scanner.nextLine(); // Consume the remaining newline character
                        String bookDoctorName = scanner.nextLine();
                        Doctor bookDoctor = null;
                        for (Doctor doctor : appointmentManager.doctors) {
                            if (doctor.getName().equals(bookDoctorName)) {
                                bookDoctor = doctor;
                                break;
                            }
                        }
                        if (bookDoctor != null) {
                            System.out.print("Enter Date: ");
                            String bookDate = scanner.nextLine();
                            System.out.print("Enter Time Slot: ");
                            String bookTimeSlot = scanner.nextLine();
                            appointmentManager.bookAppointment(appointmentManager.visitors.get(bookVisitorIndex),
                                    bookDoctor, bookDate, bookTimeSlot);
                        } else {
                            System.out.println("Doctor not found.");
                        }
                    } else {
                        System.out.println("Invalid Visitor Index.");
                    }
                    break;
                case 7:
                    System.out.print("Enter Appointment Index to Edit/Cancel: ");
                    int appointmentIndex = scanner.nextInt() - 1;
                    if (appointmentIndex >= 0 && appointmentIndex < appointmentManager.appointments.size()) {
                        System.out.print("Enter Visitor Name: ");
                        scanner.nextLine(); // Consume the remaining newline character
                        String editVisitorName = scanner.nextLine();
                        System.out.print("Enter Doctor Name: ");
                        String editDoctorName = scanner.nextLine();
                        Doctor editDoctor = null;
                        for (Doctor doctor : appointmentManager.doctors) {
                            if (doctor.getName().equals(editDoctorName)) {
                                editDoctor = doctor;
                                break;
                            }
                        }
                        if (editDoctor != null) {
                            System.out.print("Enter Date: ");
                            String editDate = scanner.nextLine();
                            System.out.print("Enter Time Slot: ");
                            String editTimeSlot = scanner.nextLine();
                            Appointment updatedAppointment = new Appointment(appointmentManager.visitors.get(appointmentIndex),
                                    editDoctor, editDate, editTimeSlot);
                            appointmentManager.editAppointment(appointmentIndex, updatedAppointment);
                        } else {
                            System.out.println("Doctor not found.");
                        }
                    } else {
                        System.out.println("Invalid Appointment Index.");
                    }
                    break;
                case 8:
                    System.out.print("Enter Doctor Name: ");
                    scanner.nextLine(); // Consume the remaining newline character
                    String doctorNameInput = scanner.nextLine();
                    Doctor existingDoctor = null;
                    int existingDoctorIndex = -1;
                    for (int i = 0; i < appointmentManager.doctors.size(); i++) {
                        if (appointmentManager.doctors.get(i).getName().equals(doctorNameInput)) {
                            existingDoctor = appointmentManager.doctors.get(i);
                            existingDoctorIndex = i;
                            break;
                        }
                    }
                    if (existingDoctor != null) {
                        System.out.print("Enter New Doctor Name: ");
                        String newDoctorName = scanner.nextLine();
                        System.out.print("Enter Specialization: ");
                        String newSpecialization = scanner.nextLine();
                        Doctor updatedDoctor = new Doctor(newDoctorName, newSpecialization);
                        appointmentManager.editDoctorDetails(existingDoctorIndex, updatedDoctor);
                    } else {
                        System.out.print("Enter Doctor Specialization: ");
                        String specialization = scanner.nextLine();
                        Doctor newDoctor = new Doctor(doctorNameInput, specialization);
                        appointmentManager.addDoctor(newDoctor);
                    }
                    break;
                case 9:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 9);

        scanner.close();
    }
}
