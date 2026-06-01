package pe.edu.upc.medibridge.payments.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.medibridge.payments.domain.model.events.PaymentFailedEvent;
import pe.edu.upc.medibridge.payments.interfaces.rest.resources.ProcessStripeWebhookRequest;

@RestController
@RequestMapping(value = "/api/v1/stripe-webhooks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Stripe Webhooks", description = "Stripe Webhook Endpoints")
public class StripeWebhookController {
    private final ApplicationEventPublisher eventPublisher;

    public StripeWebhookController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    public ResponseEntity<Void> processWebhook(@RequestBody ProcessStripeWebhookRequest resource) {
        if ("payment_intent.payment_failed".equals(resource.eventType())) {
            eventPublisher.publishEvent(new PaymentFailedEvent(resource.userId(), resource.stripePaymentIntentId()));
        }
        return ResponseEntity.ok().build();
    }
}
