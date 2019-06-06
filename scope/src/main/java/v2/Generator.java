package v2;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class Generator {
	@Lookup
	protected Window create() {
		throw new UnsupportedOperationException();
	}
}