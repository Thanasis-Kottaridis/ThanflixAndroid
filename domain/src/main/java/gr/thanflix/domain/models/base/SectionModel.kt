package gr.thanflix.domain.models.base

data class SectionModel<Section, ItemType>(
    val model: Section,
    val items: List<ItemType>
)