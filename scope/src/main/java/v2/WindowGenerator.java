package v2;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class WindowGenerator {
	@Lookup
	protected Window newWindow() {
		throw new UnsupportedOperationException();
	}
}