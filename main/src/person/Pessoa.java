package person;

import utils.DateFormat;

import java.util.Date;

public class Pessoa {
    long cpf;
    long rg;
    String name;
    Date nascimento;
    String naturalidade;

    public Pessoa(long cpf, long rg, String name, Date nascimento, String naturalidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.name = name;
        this.nascimento = nascimento;
        this.naturalidade = naturalidade;
    }

    public long getCpf() {
        return cpf;
    }

    public long getRg() {
        return rg;
    }

    public String getName() {
        return name;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\nCPF: " + cpf + "\nRG: " + rg + "\nNascimento: " + DateFormat.format(nascimento) + "\nNaturalidade: " + naturalidade + "\n";
    }
}
