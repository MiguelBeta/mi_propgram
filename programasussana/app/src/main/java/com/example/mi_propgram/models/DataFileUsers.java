package com.example.mi_propgram.models;

public class DataFileUsers {

    public String FechaInicialCita;
    public String Duracion;
    public String PacienteNombre;
    public String PacienteDocumento;
    public String PacienteTelefono;
    public String Paciente_Sexo;
    public String Paciente_EdadPaciente;
    public String Actividad_Nombre;
    public String TipoCita;
    public String TurnoMedico_CentroAtencion_Codigo;
    public String TurnoMedico_entroAtencion_Nombre;
    public String TurnoMedico_Medico_Codigo;
    public String TurnoMedico_Medico_NombreCompleto;
    public String TurnoMedico_FechaTurno;
    public String Especialidad_Descripcion;

    // Constructor vacío necesario para Firebase
    public DataFileUsers() {
    }

    public DataFileUsers(String fechaInicialCita, String duracion, String pacienteNombre, String pacienteDocumento,
                     String pacienteTelefono, String paciente_Sexo, String paciente_EdadPaciente,
                     String actividad_Nombre, String tipoCita, String turnoMedico_CentroAtencion_Codigo,
                     String turnoMedico_entroAtencion_Nombre, String turnoMedico_Medico_Codigo,
                     String turnoMedico_Medico_NombreCompleto, String turnoMedico_FechaTurno,
                     String especialidad_Descripcion) {
        FechaInicialCita = fechaInicialCita;
        Duracion = duracion;
        PacienteNombre = pacienteNombre;
        PacienteDocumento = pacienteDocumento;
        PacienteTelefono = pacienteTelefono;
        Paciente_Sexo = paciente_Sexo;
        Paciente_EdadPaciente = paciente_EdadPaciente;
        Actividad_Nombre = actividad_Nombre;
        TipoCita = tipoCita;
        TurnoMedico_CentroAtencion_Codigo = turnoMedico_CentroAtencion_Codigo;
        TurnoMedico_entroAtencion_Nombre = turnoMedico_entroAtencion_Nombre;
        TurnoMedico_Medico_Codigo = turnoMedico_Medico_Codigo;
        TurnoMedico_Medico_NombreCompleto = turnoMedico_Medico_NombreCompleto;
        TurnoMedico_FechaTurno = turnoMedico_FechaTurno;
        Especialidad_Descripcion = especialidad_Descripcion;
    }
}

