package com.team14.cms;

public class UserDisplayVisitor implements UserPartVisitor {
    @Override
    public void visit(Student student) {
        System.out.println(student.toString());
    }

    @Override
    public void visit(Administration administration) {
        System.out.println(administration.toString());
    }

    @Override
    public void visit(Professor professor) {
        System.out.println(professor.toString());
    }
}
