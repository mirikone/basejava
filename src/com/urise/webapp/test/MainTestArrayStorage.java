package com.urise.webapp.test;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;

public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume();
        resume1.setUuid("uuid1");
        Resume resume2 = new Resume();
        resume2.setUuid("uuid2");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);

        printAll();

        ARRAY_STORAGE.get(resume1.getUuid());
        ARRAY_STORAGE.update(resume1);
        ARRAY_STORAGE.delete("uuid2");

        printAll();
    }
    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
