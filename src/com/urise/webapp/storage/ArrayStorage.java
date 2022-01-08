package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public Resume[] storage = new Resume[10000];
    public int size;

    public int getIndexNumber(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume resume) {
        if(getIndexNumber(resume.getUuid()) != -1) {
            System.out.println("Resume also ready");
        } else if(size >= 10000) {
            System.out.println("OVERFLOW");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
            if (getIndexNumber(resume.getUuid()) != -1) {
                storage[getIndexNumber(resume.getUuid())] = resume;
            } else {
                System.out.println("Resume not found for update");
            }
    }

    public Resume get(String uuid) {
        if (getIndexNumber(uuid) != -1) {
            return storage[getIndexNumber(uuid)];
        } else {
            return null;
        }
    }

    public void delete(String uuid) {
        if (getIndexNumber(uuid) == -1) {
            System.out.println("Resume not found");
        } else {
            storage[getIndexNumber(uuid)] = storage[size - 1];
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
