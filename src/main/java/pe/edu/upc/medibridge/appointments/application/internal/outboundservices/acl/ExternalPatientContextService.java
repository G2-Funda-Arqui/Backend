package pe.edu.upc.medibridge.appointments.application.internal.outboundservices.acl;

public interface ExternalPatientContextService {

    /*
     * Current behavior: Health Monitoring does not exist yet, so appointments
     * stores patientId as an external reference without validating it.
     *
     * Post Health Monitoring implementation: add an adapter that implements this
     * port and delegates to Health Monitoring to verify that the patient exists.
     * If appointments later becomes a microservice, only that adapter changes
     * from an in-process call to HTTP/gRPC/events.
     */
    boolean existsByPatientId(Long patientId);
}
