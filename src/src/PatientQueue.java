package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PatientQueue {
    private Queue<Patient> queue;

    public PatientQueue() {
        queue = new LinkedList<>();
    }

    public void addPatient(Patient patient) {
        queue.offer(patient);
    }

    public Patient viewNextPatient() {
        return queue.poll();
    }

    public List<Patient> getAllPatients() {
        return List.copyOf(queue);
    }
}
