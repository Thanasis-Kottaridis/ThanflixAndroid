package gr.thanflix.data.network.mappers.common

import gr.thanflix.data.network.dto.common.ProductionCompanyDto
import gr.thanflix.domain.models.show.ProductionCompany
import gr.thanflix.domain.utils.helpers.DomainMapper

class ProductionCompaniesMapper: DomainMapper<ProductionCompanyDto, ProductionCompany> {
    override fun modelToDomain(model: ProductionCompanyDto): ProductionCompany {
        return ProductionCompany(
            id = model.id,
            logoPath = model.logoPath,
            name =model.name
        )
    }

    override fun domainToModel(domainModel: ProductionCompany): ProductionCompanyDto {
        TODO("Not yet implemented")
    }
}