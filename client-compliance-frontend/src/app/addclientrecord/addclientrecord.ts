import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { FieldsetModule } from 'primeng/fieldset';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Clientrecordservice } from '../services/clientrecordservice';

@Component({
  selector: 'app-addclientrecord',
  standalone: true,
  imports: [ CommonModule, FormsModule, FieldsetModule, ButtonModule, InputMaskModule, 
    InputTextModule, SelectModule
  ],
  templateUrl: './addclientrecord.html'
})
export class Addclientrecord {

  constructor(
    private clientservice: Clientrecordservice,
    private router: Router
  ) {}

  newClient = {
    id: '',
    domicile: '',
    email: '',
    firstName: '',
    lastName: '',
    phoneNumber: '',
    sector: '',
    type: ''
  };

  sectors = [
  { label: 'Agriculture', value: 'AGRICULTURE' },
  { label: 'Construction', value: 'CONSTRUCTION' },
  { label: 'Crypto Assets', value: 'CRYPTO_ASSETS' },
  { label: 'Defense Arms', value: 'DEFENSE_ARMS' },
  { label: 'Education', value: 'EDUCATION' },
  { label: 'Entertainment & Media', value: 'ENTERTAINMENT_MEDIA' },
  { label: 'Financial Services', value: 'FINANCIAL_SERVICES' },
  { label: 'Gambling', value: 'GAMBLING' },
  { label: 'Government', value: 'GOVERNMENT' },
  { label: 'Healthcare', value: 'HEALTHCARE' },
  { label: 'Hospitality', value: 'HOSPITALITY' },
  { label: 'Insurance', value: 'INSURANCE' },
  { label: 'Manufacturing', value: 'MANUFACTURING' },
  { label: 'Mining & Energy', value: 'MINING_ENERGY' },
  { label: 'Money Services Business', value: 'MONEY_SERVICES' },
  { label: 'Non-Profit / Charity', value: 'NON_PROFIT_OR_CHARITY' },
  { label: 'Professional Services', value: 'PROFESSIONAL_SERVICES' },
  { label: 'Real Estate', value: 'REAL_ESTATE' },
  { label: 'Retail', value: 'RETAIL' },
  { label: 'Technology', value: 'TECHNOLOGY' },
  { label: 'Telecommunications', value: 'TELECOMMUNICATIONS' },
  { label: 'Transportation & Logistics', value: 'TRANSPORTATION_LOGISTICS' },
  { label: 'Utilities', value: 'UTILITIES' },
  { label: 'Wholesale', value: 'WHOLESALE' },
  { label: 'Other', value: 'OTHER' }
];

domiciles = [
  { label: 'Afghanistan', value: 'AFGHANISTAN' },
  { label: 'Albania', value: 'ALBANIA' },
  { label: 'Algeria', value: 'ALGERIA' },
  { label: 'Andorra', value: 'ANDORRA' },
  { label: 'Angola', value: 'ANGOLA' },
  { label: 'Argentina', value: 'ARGENTINA' },
  { label: 'Armenia', value: 'ARMENIA' },
  { label: 'Australia', value: 'AUSTRALIA' },
  { label: 'Austria', value: 'AUSTRIA' },
  { label: 'Azerbaijan', value: 'AZERBAIJAN' },

  { label: 'Bahamas', value: 'BAHAMAS' },
  { label: 'Bahrain', value: 'BAHRAIN' },
  { label: 'Bangladesh', value: 'BANGLADESH' },
  { label: 'Barbados', value: 'BARBADOS' },
  { label: 'Belarus', value: 'BELARUS' },
  { label: 'Belgium', value: 'BELGIUM' },
  { label: 'Belize', value: 'BELIZE' },
  { label: 'Benin', value: 'BENIN' },
  { label: 'Bhutan', value: 'BHUTAN' },
  { label: 'Bolivia', value: 'BOLIVIA' },
  { label: 'Bosnia and Herzegovina', value: 'BOSNIA_AND_HERZEGOVINA' },
  { label: 'Botswana', value: 'BOTSWANA' },
  { label: 'Brazil', value: 'BRAZIL' },
  { label: 'Brunei', value: 'BRUNEI' },
  { label: 'Bulgaria', value: 'BULGARIA' },
  { label: 'Burkina Faso', value: 'BURKINA_FASO' },
  { label: 'Burundi', value: 'BURUNDI' },

  { label: 'Cambodia', value: 'CAMBODIA' },
  { label: 'Cameroon', value: 'CAMEROON' },
  { label: 'Canada', value: 'CANADA' },
  { label: 'Cape Verde', value: 'CAPE_VERDE' },
  { label: 'Chad', value: 'CHAD' },
  { label: 'Chile', value: 'CHILE' },
  { label: 'China', value: 'CHINA' },
  { label: 'Colombia', value: 'COLOMBIA' },
  { label: 'Congo', value: 'CONGO' },
  { label: 'Costa Rica', value: 'COSTA_RICA' },
  { label: 'Croatia', value: 'CROATIA' },
  { label: 'Cuba', value: 'CUBA' },
  { label: 'Cyprus', value: 'CYPRUS' },
  { label: 'Czechia', value: 'CZECHIA' },

  { label: 'Denmark', value: 'DENMARK' },
  { label: 'Djibouti', value: 'DJIBOUTI' },
  { label: 'Dominica', value: 'DOMINICA' },
  { label: 'Dominican Republic', value: 'DOMINICAN_REPUBLIC' },

  { label: 'Ecuador', value: 'ECUADOR' },
  { label: 'Egypt', value: 'EGYPT' },
  { label: 'El Salvador', value: 'EL_SALVADOR' },
  { label: 'Estonia', value: 'ESTONIA' },
  { label: 'Eswatini', value: 'ESWATINI' },
  { label: 'Ethiopia', value: 'ETHIOPIA' },

  { label: 'Fiji', value: 'FIJI' },
  { label: 'Finland', value: 'FINLAND' },
  { label: 'France', value: 'FRANCE' },

  { label: 'Gabon', value: 'GABON' },
  { label: 'Gambia', value: 'GAMBIA' },
  { label: 'Georgia', value: 'GEORGIA' },
  { label: 'Germany', value: 'GERMANY' },
  { label: 'Ghana', value: 'GHANA' },
  { label: 'Greece', value: 'GREECE' },
  { label: 'Guatemala', value: 'GUATEMALA' },
  { label: 'Guinea', value: 'GUINEA' },
  { label: 'Guyana', value: 'GUYANA' },

  { label: 'Haiti', value: 'HAITI' },
  { label: 'Honduras', value: 'HONDURAS' },
  { label: 'Hungary', value: 'HUNGARY' },

  { label: 'Iceland', value: 'ICELAND' },
  { label: 'India', value: 'INDIA' },
  { label: 'Indonesia', value: 'INDONESIA' },
  { label: 'Iran', value: 'IRAN' },
  { label: 'Iraq', value: 'IRAQ' },
  { label: 'Ireland', value: 'IRELAND' },
  { label: 'Israel', value: 'ISRAEL' },
  { label: 'Italy', value: 'ITALY' },

  { label: 'Jamaica', value: 'JAMAICA' },
  { label: 'Japan', value: 'JAPAN' },
  { label: 'Jordan', value: 'JORDAN' },

  { label: 'Kazakhstan', value: 'KAZAKHSTAN' },
  { label: 'Kenya', value: 'KENYA' },
  { label: 'Kuwait', value: 'KUWAIT' },
  { label: 'Kyrgyzstan', value: 'KYRGYZSTAN' },

  { label: 'Laos', value: 'LAOS' },
  { label: 'Latvia', value: 'LATVIA' },
  { label: 'Lebanon', value: 'LEBANON' },
  { label: 'Lesotho', value: 'LESOTHO' },
  { label: 'Liberia', value: 'LIBERIA' },
  { label: 'Libya', value: 'LIBYA' },
  { label: 'Liechtenstein', value: 'LIECHTENSTEIN' },
  { label: 'Lithuania', value: 'LITHUANIA' },
  { label: 'Luxembourg', value: 'LUXEMBOURG' },

  { label: 'Madagascar', value: 'MADAGASCAR' },
  { label: 'Malawi', value: 'MALAWI' },
  { label: 'Malaysia', value: 'MALAYSIA' },
  { label: 'Maldives', value: 'MALDIVES' },
  { label: 'Mali', value: 'MALI' },
  { label: 'Malta', value: 'MALTA' },
  { label: 'Mexico', value: 'MEXICO' },
  { label: 'Moldova', value: 'MOLDOVA' },
  { label: 'Monaco', value: 'MONACO' },
  { label: 'Mongolia', value: 'MONGOLIA' },
  { label: 'Montenegro', value: 'MONTENEGRO' },
  { label: 'Morocco', value: 'MOROCCO' },
  { label: 'Mozambique', value: 'MOZAMBIQUE' },
  { label: 'Myanmar', value: 'MYANMAR' },

  { label: 'Namibia', value: 'NAMIBIA' },
  { label: 'Nepal', value: 'NEPAL' },
  { label: 'Netherlands', value: 'NETHERLANDS' },
  { label: 'New Zealand', value: 'NEW_ZEALAND' },
  { label: 'Nicaragua', value: 'NICARAGUA' },
  { label: 'Niger', value: 'NIGER' },
  { label: 'Nigeria', value: 'NIGERIA' },
  { label: 'North Korea', value: 'NORTH_KOREA' },
  { label: 'North Macedonia', value: 'NORTH_MACEDONIA' },
  { label: 'Norway', value: 'NORWAY' },

  { label: 'Oman', value: 'OMAN' },

  { label: 'Pakistan', value: 'PAKISTAN' },
  { label: 'Panama', value: 'PANAMA' },
  { label: 'Papua New Guinea', value: 'PAPUA_NEW_GUINEA' },
  { label: 'Paraguay', value: 'PARAGUAY' },
  { label: 'Peru', value: 'PERU' },
  { label: 'Philippines', value: 'PHILIPPINES' },
  { label: 'Poland', value: 'POLAND' },
  { label: 'Portugal', value: 'PORTUGAL' },

  { label: 'Qatar', value: 'QATAR' },

  { label: 'Romania', value: 'ROMANIA' },
  { label: 'Russia', value: 'RUSSIA' },
  { label: 'Rwanda', value: 'RWANDA' },

  { label: 'Saudi Arabia', value: 'SAUDI_ARABIA' },
  { label: 'Senegal', value: 'SENEGAL' },
  { label: 'Serbia', value: 'SERBIA' },
  { label: 'Singapore', value: 'SINGAPORE' },
  { label: 'Slovakia', value: 'SLOVAKIA' },
  { label: 'Slovenia', value: 'SLOVENIA' },
  { label: 'Somalia', value: 'SOMALIA' },
  { label: 'South Africa', value: 'SOUTH_AFRICA' },
  { label: 'South Korea', value: 'SOUTH_KOREA' },
  { label: 'Spain', value: 'SPAIN' },
  { label: 'Sri Lanka', value: 'SRI_LANKA' },
  { label: 'Sudan', value: 'SUDAN' },
  { label: 'Sweden', value: 'SWEDEN' },
  { label: 'Switzerland', value: 'SWITZERLAND' },
  { label: 'Syria', value: 'SYRIA' },

  { label: 'Taiwan', value: 'TAIWAN' },
  { label: 'Tajikistan', value: 'TAJIKISTAN' },
  { label: 'Tanzania', value: 'TANZANIA' },
  { label: 'Thailand', value: 'THAILAND' },
  { label: 'Togo', value: 'TOGO' },
  { label: 'Tunisia', value: 'TUNISIA' },
  { label: 'Turkey', value: 'TURKEY' },
  { label: 'Turkmenistan', value: 'TURKMENISTAN' },

  { label: 'Uganda', value: 'UGANDA' },
  { label: 'Ukraine', value: 'UKRAINE' },
  { label: 'United Arab Emirates', value: 'UNITED_ARAB_EMIRATES' },
  { label: 'United Kingdom', value: 'UNITED_KINGDOM' },
  { label: 'United States', value: 'UNITED_STATES' },
  { label: 'Uruguay', value: 'URUGUAY' },
  { label: 'Uzbekistan', value: 'UZBEKISTAN' },

  { label: 'Venezuela', value: 'VENEZUELA' },
  { label: 'Vietnam', value: 'VIETNAM' },

  { label: 'Yemen', value: 'YEMEN' },

  { label: 'Zambia', value: 'ZAMBIA' },
  { label: 'Zimbabwe', value: 'ZIMBABWE' },

  { label: 'Other', value: 'OTHER' }
]

clienttypes = [
  { label: 'Individual', value: 'INDIVIDUAL' },
  { label: 'Corporate', value: 'CORPORATE' },
  { label: 'Institutional', value: 'INSTITUTIONAL' }
];

  createClient() {

    this.clientservice.addUser(this.newClient).subscribe({
      next: () => {
        this.resetClient();
        this.router.navigate(['/viewclientrecord']); 
      },
      error: (err) => {
        console.error('Error creating client', err);
      }
    });
  }

  resetClient() {
    this.newClient = {
      id: '',
      domicile: '',
      email: '',
      firstName: '',
      lastName: '',
      phoneNumber: '',
      sector: '',
      type: ''
    };
  }
}