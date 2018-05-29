package ru.otus.t1.command;

import ru.otus.t1.exception.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
