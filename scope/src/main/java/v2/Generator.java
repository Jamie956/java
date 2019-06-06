package v2;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class Generator {
	@Lookup
	protected abstract Window create();
}