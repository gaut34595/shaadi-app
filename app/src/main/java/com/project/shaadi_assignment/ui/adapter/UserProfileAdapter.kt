package com.project.shaadi_assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.project.shaadi_assignment.R
import com.project.shaadi_assignment.data.model.MatchStatus
import com.project.shaadi_assignment.data.model.UserProfile

class UserProfileAdapter(
    private val onAccept: (UserProfile) -> Unit,
    private val onDecline: (UserProfile) -> Unit
) : ListAdapter<UserProfile, UserProfileAdapter.ProfileViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImage: ImageView = itemView.findViewById(R.id.ivProfile)
        private val nameText: TextView = itemView.findViewById(R.id.tvName)
        private val locationText: TextView = itemView.findViewById(R.id.tvLocation)
        private val ageText: TextView = itemView.findViewById(R.id.tvAge)
        private val acceptButton: MaterialButton = itemView.findViewById(R.id.btnAccept)
        private val declineButton: MaterialButton = itemView.findViewById(R.id.btnDecline)
        private val statusText: TextView = itemView.findViewById(R.id.tvStatus)
        private val actionButtonsLayout: View = itemView.findViewById(R.id.layoutActionButtons)

        fun bind(profile: UserProfile) {
            // Load profile image
            Glide.with(profileImage)
                .load(profile.pictureUrl)
                .centerCrop()
                .into(profileImage)

            // Set text fields
            nameText.text = "${profile.name}"
            locationText.text = "${profile.location}"
            ageText.text = "Age: ${profile.age}"

            // Handle status and buttons visibility
            when (profile.status) {
                MatchStatus.ACCEPTED -> {
                    actionButtonsLayout.visibility = View.GONE
                    statusText.visibility = View.VISIBLE
                    statusText.text = "Status: Accepted"
                    statusText.setTextColor(itemView.context.getColor(R.color.success_color))
                }
                MatchStatus.DECLINED -> {
                    actionButtonsLayout.visibility = View.GONE
                    statusText.visibility = View.VISIBLE
                    statusText.text = "Status: Declined"
                    statusText.setTextColor(itemView.context.getColor(R.color.error_color))
                }
                MatchStatus.PENDING -> {
                    actionButtonsLayout.visibility = View.VISIBLE
                    statusText.visibility = View.GONE
                }
            }

            // Set click listeners
            acceptButton.setOnClickListener { onAccept(profile) }
            declineButton.setOnClickListener { onDecline(profile) }
        }
    }

    private class ProfileDiffCallback : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }
    }
} 