package nl.softwarestrijders.waiter.order.core.domain.id;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@TestExcludeGenerated
@Document("product")
public record ProductId(UUID id) {
}
