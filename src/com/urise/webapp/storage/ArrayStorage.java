package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10000;

    private final Resume[] storage = new Resume[10000];
    private int size;

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume resume) {
        if (findIndex(resume.getUuid()) != -1) {
            System.out.println("Resume also ready");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("OVERFLOW");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int i = findIndex(resume.getUuid());
        if (i != -1) {
            storage[i] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found for update");
        }
    }

    public Resume get(String uuid) {
        if (findIndex(uuid) != -1) {
            return storage[findIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("Resume " + uuid + " not found");
        } else {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
