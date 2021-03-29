package com.team14.cms;

public class UserDisplayVisitor implements UserVisitor {
    @Override
    public void visit(Student student) {
        System.out.println(student.toString());
    }

    @Override
    public void visit(Professor professor) {
        System.out.println(professor.toString());
    }
}
