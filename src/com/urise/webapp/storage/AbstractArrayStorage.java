package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("Resume also ready");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("OVERFLOW");
        } else {
            insertResume(resume, index);
            size++;
        }
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found for update");
        }
    }

    public Resume get(String uuid) {
        if (findIndex(uuid) > 0) {
            return storage[findIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not found");
        } else {
            deleteResume(index);
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

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract int findIndex(String uuid);
}