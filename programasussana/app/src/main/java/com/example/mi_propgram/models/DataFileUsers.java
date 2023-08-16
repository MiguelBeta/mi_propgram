package com.example.mi_propgram.models;


public class DataFileUsers {

    public String actividadNombre;
    public String duracion;
    public String especialidadDescripcion;
    public String fechaInicialCita;
    public String pacienteDocumento;
    public String pacienteNombre;
    public String pacienteTelefono;
    public String pacienteEdadPaciente;
    public String pacienteSexo;
    public String tipoCita;
    public String turnoMedicoCentroAtencionCodigo;
    public String turnoMedicoFechaTurno;
    public String turnoMedicoMedicoCodigo;
    public String turnoMedicoMedicoNombreCompleto;
    public String turnoMedicoentroAtencionNombre;


    public DataFileUsers() {
    }

    public DataFileUsers(String actividadNombre, String duracion, String especialidadDescripcion, String fechaInicialCita, String pacienteDocumento, String pacienteNombre, String pacienteTelefono, String pacienteEdadPaciente, String pacienteSexo, String tipoCita, String turnoMedicoCentroAtencionCodigo, String turnoMedicoFechaTurno, String turnoMedicoMedicoCodigo, String turnoMedicoMedicoNombreCompleto, String turnoMedicoentroAtencionNombre) {
        this.actividadNombre = actividadNombre;
        this.duracion = duracion;
        this.especialidadDescripcion = especialidadDescripcion;
        this.fechaInicialCita = fechaInicialCita;
        this.pacienteDocumento = pacienteDocumento;
        this.pacienteNombre = pacienteNombre;
        this.pacienteTelefono = pacienteTelefono;
        this.pacienteEdadPaciente = pacienteEdadPaciente;
        this.pacienteSexo = pacienteSexo;
        this.tipoCita = tipoCita;
        this.turnoMedicoCentroAtencionCodigo = turnoMedicoCentroAtencionCodigo;
        this.turnoMedicoFechaTurno = turnoMedicoFechaTurno;
        this.turnoMedicoMedicoCodigo = turnoMedicoMedicoCodigo;
        this.turnoMedicoMedicoNombreCompleto = turnoMedicoMedicoNombreCompleto;
        this.turnoMedicoentroAtencionNombre = turnoMedicoentroAtencionNombre;
    }

    @Override
    public String toString() {
        return "DataFileUsers{" +
                "actividadNombre='" + actividadNombre + '\'' +
                ", duracion='" + duracion + '\'' +
                ", especialidadDescripcion='" + especialidadDescripcion + '\'' +
                ", fechaInicialCita='" + fechaInicialCita + '\'' +
                ", pacienteDocumento='" + pacienteDocumento + '\'' +
                ", pacienteNombre='" + pacienteNombre + '\'' +
                ", pacienteTelefono='" + pacienteTelefono + '\'' +
                ", pacienteEdadPaciente='" + pacienteEdadPaciente + '\'' +
                ", pacienteSexo='" + pacienteSexo + '\'' +
                ", tipoCita='" + tipoCita + '\'' +
                ", turnoMedicoCentroAtencionCodigo='" + turnoMedicoCentroAtencionCodigo + '\'' +
                ", turnoMedicoFechaTurno='" + turnoMedicoFechaTurno + '\'' +
                ", turnoMedicoMedicoCodigo='" + turnoMedicoMedicoCodigo + '\'' +
                ", turnoMedicoMedicoNombreCompleto='" + turnoMedicoMedicoNombreCompleto + '\'' +
                ", turnoMedicoentroAtencionNombre='" + turnoMedicoentroAtencionNombre + '\'' +
                '}';
    }
}

