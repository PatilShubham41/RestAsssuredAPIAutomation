package day6.rest.assured;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class MixIn {
	
	@JsonIgnore abstract int ignoreThis();

}
