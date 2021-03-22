package com.velocip.unrdapp.data.models


data class StoryResult(
    val result: Result? = null,
    val status: Status? = null
)


data class Result(
        val age_from: Int? = null,
        val age_to: Int? = null,
        val background_image: List<BackgroundImage?>? = null,
        val characters: List<Character?>? = null,
        val contacts: List<Any?>? = null,
        val created: String? = null,
        val duration: String? = null,
        val full_summary: String? = null,
        val genre_id: Int? = null,
        val intro_video: List<IntroVideo?>? = null,
        val intro_video_sequence: Int? = null,
        val is_coming_soon: Boolean? = null,
        val is_early_access: Boolean? = null,
        val is_featured: Boolean? = null,
        val is_in_testing: Boolean? = null,
        val is_owned: Boolean? = null,
        val is_published: Boolean? = null,
        val language_id: Int? = null,
        val last_progress_report: Any? = null,
        val list_image: List<ImageX?>? = null,
        val main_character_id: Int? = null,
        val name: String? = null,
        val passcode_clue: Any? = null,
        val passcode_value: Any? = null,
        val preview_media: List<PreviewMedia?>? = null,
        val price: Int? = null,
        val progress: Int? = null,
        val publication_status: String? = null,
        val purchase_date: String? = null,
        val purchased_items: List<PurchasedItem?>? = null,
        val release_date: Any? = null,
        val release_timezone: Any? = null,
        val short_summary: String? = null,
        val story_end_sequence: Int? = null,
        val story_id: Int? = null,
        val story_start_sequence: Int? = null,
        val timelines: List<Timeline?>? = null,
        val updated: String? = null
)


fun StoryResult.toStory(): Story{
    val result = result

    val storyCharacters = result?.characters?.map {
        StoryCharacter(
                characterId = it?.character_id.toString(),
                name = it?.name ?: "",
                imageUrl = it?.image?.resource_uri ?: ""
        )
    }

    val story =  Story(
            storyId = result?.story_id.toString(),
            created = result?.created ?: "",
            duration = result?.duration ?: "",
            full_summary = result?.full_summary ?: "",
            name = result?.name ?: "",
            shortSummary = result?.short_summary ?: "",
            price = result?.price?.toDouble(),
            ageFrom = result?.age_from ?: 0,
            updated = result?.updated ?: "",
            storyImage = result?.list_image?.get(0)?.resource_uri ?: "",
            videoUrls = result?.preview_media?.map { it?.resource_uri ?: "" } ?: emptyList(),
            backgroundImage = result?.background_image?.get(0)?.resource_uri ?: ""
    )

    story.storyCharacters = storyCharacters ?: emptyList()

    return story
}


data class Status(
    val code: Int? = null,
    val message: String? = null
)



data class BackgroundImage(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)



data class Character(
    val character_id: Int? = null,
    val image: Image? = null,
    val is_main: Boolean? = null,
    val name: String? = null
)



data class IntroVideo(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)



data class ImageX(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)



data class PreviewMedia(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)



data class PurchasedItem(
    val exchange_key: Int? = null,
    val exchange_type: String? = null
)



data class Timeline(
    val chats: List<Chat?>? = null,
    val created: String? = null,
    val events: List<Event?>? = null,
    val is_primary: Boolean? = null,
    val is_terminal: Boolean? = null,
    val name: String? = null,
    val timeline_id: Int? = null,
    val updated: String? = null
)



data class Image(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)



data class Chat(
    val chat_id: Int? = null,
    val display_name: String? = null,
    val is_group: Boolean? = null,
    val is_locked: Boolean? = null,
    val name: String? = null,
    val owned: Any? = null,
    val price: Int? = null,
    val timeline_id: Int? = null
)



data class Event(
    val `data`: Data? = null,
    val has_options: Boolean? = null,
    val sequence: Int? = null,
    val type: String? = null
)



data class Data(
    val background_colour: String? = null,
    val character_id: Int? = null,
    val character_share_id: Int? = null,
    val chat_action_id: Int? = null,
    val chat_id: Int? = null,
    val chat_message_id: Int? = null,
    val content: String? = null,
    val created: String? = null,
    val duration: Int? = null,
    val has_options: Boolean? = null,
    val has_url: Boolean? = null,
    val is_live: Boolean? = null,
    val is_locked: Boolean? = null,
    val is_public: Boolean? = null,
    val media: List<Media?>? = null,
    val media_duration: Any? = null,
    val options_timeout: Int? = null,
    val owned: Any? = null,
    val price: Int? = null,
    val resource_id: Any? = null,
    val sequence: Int? = null,
    val stream_path: Any? = null,
    val thumb: List<Thumb?>? = null,
    val thumb_resource_id: Any? = null,
    val updated: String? = null,
    val url_label: Any? = null
)



data class Media(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)



data class Thumb(
    val resource_fid: String? = null,
    val resource_id: Int? = null,
    val resource_preset: String? = null,
    val resource_processed: Boolean? = null,
    val resource_progress: Int? = null,
    val resource_type: String? = null,
    val resource_uri: String? = null
)