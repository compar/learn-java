package compar.demo.java8fun;

class StringCombiner {

	private final String prefix;
	private final String suffix;
	private final String delim;
	private final StringBuilder builder;

	public StringCombiner(String delim, String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.delim = delim;
		this.builder = new StringBuilder();
	}

	public StringCombiner add(String element) {
		if (areAtStart()) {
			builder.append(prefix);
		} else {
			builder.append(delim);
		}
		builder.append(element);

		return this;
	}

	public StringCombiner merge(StringCombiner other) {
		builder.append(other.builder);
		return this;
	}
	
    // BEGIN toString
    @Override
    public String toString() {
        return prefix + builder.toString() + suffix;
    }
    // END toString

    // BEGIN areAtStart
    private boolean areAtStart() {
        return builder.length() == 0;
    }
    // END areAtStart
}
