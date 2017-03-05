package com.vliolios.tmdb.search;

import org.springframework.web.client.RestTemplate;

public class MultiSearch extends Search<MultiResult> {
	
	private String language;
	private Boolean includeAdult;
	private String region;

	private MultiSearch(String apiKey, RestTemplate restTemplate) {
		super(apiKey, restTemplate);
	}

	public static SearchWithQuery apiKey(String apiKey, RestTemplate restTemplate) {
		return new Builder(apiKey, restTemplate);
	}
	
	public String getLanguage() {
		return language;
	}
	
	public Boolean getIncludeAdult() {
		return includeAdult;
	}

	public String getRegion() {
		return region;
	}
	
	@Override
	public String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		if (includeAdult != null) {
			sb.append("&include_adult=").append(includeAdult.toString());
		}
		if (region != null) {
			sb.append("&region=").append(region);
		}
		return sb.toString();
		
	}

	@Override
	public String getType() {
		return "multi";
	}

	@Override
	public Class<MultiResult> getResponseType() {
		return MultiResult.class;
	}

	public static class Builder implements SearchWithQuery {
		MultiSearch multiSearch;

		private Builder(String apiKey, RestTemplate restTemplate) {
			this.multiSearch = new MultiSearch(apiKey, restTemplate);
		}

		public Builder query(String query) {
			multiSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			multiSearch.page(page);
			return this;
		}

		public Builder language(String language) {
			multiSearch.language = language;
			return this;
		}

		public Builder includeAdult(Boolean includeAdult) {
			multiSearch.includeAdult = includeAdult;
			return this;
		}

		public Builder region(String region) {
			multiSearch.region = region;
			return this;
		}

		public MultiSearch build() {
			return multiSearch;
		}
	}

	public interface SearchWithQuery {
		Builder query(String query);
	}

}