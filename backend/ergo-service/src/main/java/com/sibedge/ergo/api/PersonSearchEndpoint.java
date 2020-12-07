package com.sibedge.ergo.api;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import com.sibedge.ergo.component.person.FindPersonsByFilterUseCase;
import com.sibedge.ergo.shared.event.AuditEvent;
import com.sibedge.ergo.shared.event.EventSender;
import com.sibedge.ergo.shared.transport.ListData;
import com.sibedge.ergo.shared.transport.PersonData;
import com.sibedge.ergo.shared.transport.PersonFilterData;
import com.sibedge.ergo.shared.data.EventKey;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Endpoint for person search.
 */
@RestController
@RequestMapping(
        path = "/persons",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class PersonSearchEndpoint {

    private final FindPersonsByFilterUseCase findPersonsByFilterUseCase;
    private final EventSender<AuditEvent> auditEventSender;

    @GetMapping
    public ListData<PersonData> findPersonsByFilter(
            @NotNull @Validated PersonFilterData personFilter,
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        notifyAuditListeners(personFilter);
        return findPersonsByFilterUseCase.execute(personFilter, pageable);
    }

    private void notifyAuditListeners(PersonFilterData personFilter) {
        var event = AuditEvent.of(
                EventKey.FILTER_PERSON_LIST,
                personFilter.toString(),
                LocalDateTime.now()
        );
        auditEventSender.send(event);
    }

}
