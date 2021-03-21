package com.velocip.unrdapp.data.models

import kotlinx.serialization.Polymorphic

@kotlinx.serialization.Serializable
data class StoryResult(
    val result: Result,
    val status: Status
)

@kotlinx.serialization.Serializable
data class Result(
    val age_from: Int,
    val age_to: Int,
    val background_image: List<BackgroundImage>,
    val characters: List<Character>,
    val contacts: List<@Polymorphic Any>,
    val created: String,
    val duration: String,
    val full_summary: String,
    val genre_id: Int,
    val intro_video: List<IntroVideo>,
    val intro_video_sequence: Int,
    val is_coming_soon: Boolean,
    val is_early_access: Boolean,
    val is_featured: Boolean,
    val is_in_testing: Boolean,
    val is_owned: Boolean,
    val is_published: Boolean,
    val language_id: Int,
    val last_progress_report: @Polymorphic Any,
    val list_image: List<ImageX>,
    val main_character_id: Int,
    val name: String,
    val passcode_clue: @Polymorphic Any,
    val passcode_value: @Polymorphic Any,
    val preview_media: List<PreviewMedia>,
    val price: Int,
    val progress: Int,
    val publication_status: String,
    val purchase_date: String,
    val purchased_items: List<PurchasedItem>,
    val release_date: @Polymorphic Any,
    val release_timezone: @Polymorphic Any,
    val short_summary: String,
    val story_end_sequence: Int,
    val story_id: Int,
    val story_start_sequence: Int,
    val timelines: List<Timeline>,
    val updated: String
)


fun StoryResult.toStory(): Story{
    val result = result
    return Story(
        storyId = result.story_id.toString()
    )
}

@kotlinx.serialization.Serializable
data class Status(
    val code: Int,
    val message: String
)


@kotlinx.serialization.Serializable
data class BackgroundImage(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)


@kotlinx.serialization.Serializable
data class Character(
    val character_id: Int,
    val image: Image,
    val is_main: Boolean,
    val name: String
)


@kotlinx.serialization.Serializable
data class IntroVideo(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)


@kotlinx.serialization.Serializable
data class ImageX(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)


@kotlinx.serialization.Serializable
data class PreviewMedia(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)


@kotlinx.serialization.Serializable
data class PurchasedItem(
    val exchange_key: Int,
    val exchange_type: String
)


@kotlinx.serialization.Serializable
data class Timeline(
    val chats: List<Chat>,
    val created: String,
    val events: List<Event>,
    val is_primary: Boolean,
    val is_terminal: Boolean,
    val name: String,
    val timeline_id: Int,
    val updated: String
)


@kotlinx.serialization.Serializable
data class Image(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)


@kotlinx.serialization.Serializable
data class Chat(
    val chat_id: Int,
    val display_name: String,
    val is_group: Boolean,
    val is_locked: Boolean,
    val name: String,
    val owned: @Polymorphic Any,
    val price: Int,
    val timeline_id: Int
)


@kotlinx.serialization.Serializable
data class Event(
    val `data`: Data,
    val has_options: Boolean,
    val sequence: Int,
    val type: String
)


@kotlinx.serialization.Serializable
data class Data(
    val background_colour: String,
    val character_id: Int,
    val character_share_id: Int,
    val chat_action_id: Int,
    val chat_id: Int,
    val chat_message_id: Int,
    val content: String,
    val created: String,
    val duration: Int,
    val has_options: Boolean,
    val has_url: Boolean,
    val is_live: Boolean,
    val is_locked: Boolean,
    val is_public: Boolean,
    val media: List<Media>,
    val media_duration: @Polymorphic Any,
    val options_timeout: Int,
    val owned: @Polymorphic Any,
    val price: Int,
    val resource_id: @Polymorphic Any,
    val sequence: Int,
    val stream_path: @Polymorphic Any,
    val thumb: List<Thumb>,
    val thumb_resource_id: @Polymorphic Any,
    val updated: String,
    val url_label: @Polymorphic Any
)


@kotlinx.serialization.Serializable
data class Media(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)


@kotlinx.serialization.Serializable
data class Thumb(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
)