package com.skillstorm.types;

public enum IndustrySector {

	AGRICULTURE("Agriculture"),
    CONSTRUCTION("Construction"),
    CRYPTO_ASSETS("Crypto / Virtual Assets"),
    DEFENSE_ARMS("Defense / Arms"),
    EDUCATION("Education"),
    ENTERTAINMENT_MEDIA("Entertainment & Media"),
    FINANCIAL_SERVICES("Financial Services"),
    GAMBLING("Gambling"),
    GOVERNMENT("Government"),
    HEALTHCARE("Healthcare"),
    HOSPITALITY("Hospitality"),
    INSURANCE("Insurance"),
    MANUFACTURING("Manufacturing"),
    MINING_ENERGY("Mining & Energy"),
    MONEY_SERVICES("Money Services Business"),
    NON_PROFIT_OR_CHARITY("Non-Profit / Charity"),
    PROFESSIONAL_SERVICES("Professional Services"),
    REAL_ESTATE("Real Estate"),
    RETAIL("Retail"),
    TECHNOLOGY("Technology"),
    TELECOMMUNICATIONS("Telecommunications"),
    TRANSPORTATION_LOGISTICS("Transportation & Logistics"),
    UTILITIES("Utilities"),
    WHOLESALE("Wholesale"),
    OTHER("Other");

    private final String sector;

    IndustrySector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }
	


}
