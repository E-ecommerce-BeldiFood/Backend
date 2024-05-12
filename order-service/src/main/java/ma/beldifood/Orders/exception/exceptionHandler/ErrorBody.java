package ma.beldifood.Orders.exception.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ErrorBody {
    @UpdateTimestamp
    private Date timestamp;
    private HttpStatus status;
    private List<String> errors;
}
